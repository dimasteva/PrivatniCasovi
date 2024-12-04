package com.jahaci.edukacija.controller;


import com.jahaci.edukacija.model.lesson.Lesson;
import com.jahaci.edukacija.model.lesson.LessonFilterModel;
import com.jahaci.edukacija.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public Lesson createLesson(@RequestBody Lesson lesson) {
        return lessonService.addLesson(lesson);
    }

    @GetMapping
    public List<Lesson> filterLessons(@RequestBody(required = false) LessonFilterModel model) {
        return lessonService.filterLessons(model);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> getLessonById(@PathVariable Integer id) {
        return lessonService.getLessonById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Integer id) {
        lessonService.deleteLesson(id);
    }

}
