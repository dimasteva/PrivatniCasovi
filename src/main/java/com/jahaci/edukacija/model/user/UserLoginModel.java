package com.jahaci.edukacija.model.user;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserLoginModel {
    private String username;
    private String password;

    public UserLoginModel(String username, String password) {
        this.username = username;
        this.password = sha256(password);
    }

    public UserLoginModel() {
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
        System.out.println(bytesToHex(encoded));
        return bytesToHex(encoded);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
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
}
