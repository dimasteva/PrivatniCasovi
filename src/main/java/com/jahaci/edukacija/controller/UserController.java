package com.jahaci.edukacija.controller;

import com.jahaci.edukacija.model.Lesson;
import com.jahaci.edukacija.model.user.User;
import com.jahaci.edukacija.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

}
