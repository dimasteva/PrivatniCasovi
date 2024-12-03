package com.jahaci.edukacija.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    @Column(nullable = false)
    private String teacher;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timeCreated;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timeScheduled;
    @Column(nullable = false)
    private String link;

    @JsonCreator
    public Lesson(String description, String teacher, String name, LocalDateTime timeScheduled, String link) {
        this.description = description;
        this.teacher = teacher;
        this.name = name;
        timeCreated = LocalDateTime.now();
        this.timeScheduled = timeScheduled;
        this.link = link;
    }

    public Lesson() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeScheduled() {
        return timeScheduled;
    }

    public void setTimeScheduled(LocalDateTime timeScheduled) {
        this.timeScheduled = timeScheduled;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
