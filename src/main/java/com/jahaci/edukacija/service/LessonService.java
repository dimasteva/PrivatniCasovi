package com.jahaci.edukacija.service;

import com.jahaci.edukacija.exception.InvalidLessonTimeException;
import com.jahaci.edukacija.model.lesson.Lesson;
import com.jahaci.edukacija.model.lesson.LessonFilterModel;
import com.jahaci.edukacija.repository.LessonRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson addLesson(Lesson lesson) {
        if(lesson.getTimeCreated().isAfter(lesson.getTimeScheduled()))
            throw new InvalidLessonTimeException("Time created cannot be after the time scheduled.");
        else
            return lessonRepository.save(lesson);
    }

    public List<Lesson> filterLessons(LessonFilterModel model) {
        if(model == null) model = new LessonFilterModel(null,null,null,null);
        LessonFilterModel finalModel = model;
        return lessonRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            System.out.println(finalModel.getName());
            if(finalModel.getTeacher() != null) predicates.add(cb.equal(root.get("teacher"), finalModel.getTeacher()));
            if (finalModel.getName() != null) predicates.add(cb.like(root.get("name"), "%" + finalModel.getName() + "%"));
            if(finalModel.getStart() != null) predicates.add(cb.greaterThanOrEqualTo(root.get("timeScheduled"), finalModel.getStart()));
            if(finalModel.getEnd() != null) predicates.add(cb.lessThanOrEqualTo(root.get("timeScheduled"), finalModel.getEnd()));

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Integer id) {
        return lessonRepository.findById(id);
    }

    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }
}
