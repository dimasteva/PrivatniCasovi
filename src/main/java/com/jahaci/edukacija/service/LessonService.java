package com.jahaci.edukacija.service;

import com.jahaci.edukacija.exception.InvalidLessonTimeException;
import com.jahaci.edukacija.exception.InvalidLoginDataException;
import com.jahaci.edukacija.model.lesson.Lesson;
import com.jahaci.edukacija.model.lesson.LessonFilterModel;
import com.jahaci.edukacija.model.user.User;
import com.jahaci.edukacija.model.user.UserAttendanceModel;
import com.jahaci.edukacija.model.user.UserLoginModel;
import com.jahaci.edukacija.model.user.UserRole;
import com.jahaci.edukacija.repository.LessonRepository;
import com.jahaci.edukacija.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public LessonService(LessonRepository lessonRepository, UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

    public Lesson addLesson(Lesson lesson) {
        if(lesson.getTimeCreated().isAfter(lesson.getTimeScheduled()))
            throw new InvalidLessonTimeException("Time created cannot be after the time scheduled.");
        else
            return lessonRepository.save(lesson);
    }

    public Lesson attend(UserAttendanceModel model) {
        Optional<User> user = userRepository.tryLogin(model.getUsername(), model.getPassword());
        if(user.isPresent()) {
            Lesson l = lessonRepository.findById(model.getLessonId()).get();
            if(!l.getAttendance().contains(user.get())) l.getAttendance().add(user.get());
            return lessonRepository.save(l);
        } else {
            throw new InvalidLoginDataException("Invalid Username or Password");
        }
    }

    public Lesson unattend(UserAttendanceModel model) {
        Optional<User> user = userRepository.tryLogin(model.getUsername(), model.getPassword());
        if(user.isPresent()) {
            Lesson l = lessonRepository.findById(model.getLessonId()).get();
            if(l.getAttendance().contains(user.get())) l.getAttendance().remove(user.get());
            return lessonRepository.save(l);
        } else {
            throw new InvalidLoginDataException("Invalid Username or Password");
        }
    }


    public List<Lesson> filterLessons(LessonFilterModel model) {
        if(model == null) model = new LessonFilterModel(null,null,null,null);
        LessonFilterModel finalModel = model;
        return lessonRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(finalModel.getTeacher() != null) predicates.add(cb.like(root.get("teacher"), "%" +finalModel.getTeacher() + "%"));
            if (finalModel.getName() != null) predicates.add(cb.like(root.get("name"), "%" + finalModel.getName() + "%"));
            if(finalModel.getStart() != null) predicates.add(cb.greaterThanOrEqualTo(root.get("timeScheduled"), finalModel.getStart()));
            if(finalModel.getEnd() != null) predicates.add(cb.lessThanOrEqualTo(root.get("timeScheduled"), finalModel.getEnd()));

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    public boolean isAttending(UserAttendanceModel model) {
        Optional<User> user = userRepository.tryLogin(model.getUsername(), model.getPassword());
        if(user.isPresent()) {
            Lesson l = lessonRepository.findById(model.getLessonId()).get();
            return l.getAttendance().contains(user.get());
        }
        throw new InvalidLoginDataException("Invalid Username or Password");
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Integer id) {
        return lessonRepository.findById(id);
    }

    public void deleteLesson(Integer id, UserLoginModel model) {
        Optional<User> user = userRepository.tryLogin(model.getUsername(), model.getPassword());
        if(user.isPresent()) if(user.get().getName().equals(lessonRepository.findById(id).get().getTeacher())) lessonRepository.deleteById(id);
    }
}
