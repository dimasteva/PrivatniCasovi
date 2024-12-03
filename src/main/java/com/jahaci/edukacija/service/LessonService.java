package com.jahaci.edukacija.service;

import com.jahaci.edukacija.exception.InvalidLessonTimeException;
import com.jahaci.edukacija.model.Lesson;
import com.jahaci.edukacija.repository.LessonRepository;
import org.springframework.stereotype.Service;

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
