package com.jahaci.edukacija.controller;


import com.jahaci.edukacija.model.lesson.Lesson;
import com.jahaci.edukacija.model.lesson.LessonFilterModel;
import com.jahaci.edukacija.model.user.UserAttendanceModel;
import com.jahaci.edukacija.model.user.UserLoginModel;
import com.jahaci.edukacija.service.LessonService;
import com.jahaci.edukacija.service.UserService;
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


    @PostMapping("/create")
    public Lesson createLesson(@RequestBody Lesson lesson) {
        return lessonService.addLesson(lesson);
    }

    @PostMapping("/attend")
    public Lesson attendLesson(@RequestBody UserAttendanceModel model) {
        return lessonService.attend(model);
    }

    @PostMapping("/unattend")
    public Lesson unattendLesson(@RequestBody UserAttendanceModel model) {
        return lessonService.unattend(model);
    }

    @PostMapping
    public List<Lesson> filterLessons(@RequestBody(required = false) LessonFilterModel model) {
        return lessonService.filterLessons(model);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> getLessonById(@PathVariable Integer id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping("/attending")
    public boolean isAttending(@RequestBody UserAttendanceModel model) {
        System.out.println(model.toString());
        return lessonService.isAttending(model);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody UserAttendanceModel model) {
        lessonService.deleteLesson(model);
    }

}
