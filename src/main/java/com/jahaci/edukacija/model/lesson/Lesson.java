package com.jahaci.edukacija.model.lesson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jahaci.edukacija.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(nullable = true)
    @ManyToMany
    private List<User> attendance = new ArrayList<>();

    @JsonCreator
    public Lesson(Integer id, String description, String teacher, String name, LocalDateTime timeScheduled, String link, List<User> attendance) {
        this.id = id;
        this.description = description;
        this.teacher = teacher;
        this.name = name;
        this.timeCreated = LocalDateTime.now();
        this.timeScheduled = timeScheduled;
        this.link = link;
        this.attendance = attendance;
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

    public List<User> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<User> attendance) {
        this.attendance = attendance;
    }
}
