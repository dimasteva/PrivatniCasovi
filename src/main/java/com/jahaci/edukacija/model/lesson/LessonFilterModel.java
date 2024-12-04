package com.jahaci.edukacija.model.lesson;

import java.time.LocalDateTime;

public class LessonFilterModel {
    private String name,teacher;
    private LocalDateTime start,end;

    public LessonFilterModel(String name, String teacher, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.teacher = teacher;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
