package com.jahaci.edukacija.model.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAttendanceModel {
    public String username,password;
    public Integer lessonId;

    public UserAttendanceModel(String username, String password, Integer lessonId) {
        this.username = username;
        this.password = password;
        this.lessonId = lessonId;
    }

    public UserAttendanceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }
}
