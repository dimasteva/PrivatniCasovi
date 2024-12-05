package com.jahaci.edukacija.controller;

import com.jahaci.edukacija.model.user.User;
import com.jahaci.edukacija.model.user.UserLoginModel;
import com.jahaci.edukacija.model.user.UserResetModel;
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

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        //userService.deleteUser(id);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLoginModel user) {
        return userService.tryLogin(user);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/reset")
    public User reset(@RequestBody UserResetModel model) {
        return userService.reset(model);
    }

}
