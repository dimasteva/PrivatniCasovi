package com.jahaci.edukacija.model.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAttendanceModel {
    public String username,password;
    public Integer lessonId;

    public UserAttendanceModel(String username, String password, Integer lessonId) {
        this.username = username;
        this.password = sha256(password);
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
        this.password = sha256(password);
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    private String sha256(String s)
    {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] encoded = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encoded);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
