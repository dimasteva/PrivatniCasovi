package com.jahaci.edukacija.service;

import com.jahaci.edukacija.exception.InvalidLoginDataException;
import com.jahaci.edukacija.exception.UsernameAlreadyExistsException;
import com.jahaci.edukacija.exception.UsernameMustContainSpaceException;
import com.jahaci.edukacija.model.user.User;
import com.jahaci.edukacija.model.user.UserLoginModel;
import com.jahaci.edukacija.model.user.UserResetModel;
import com.jahaci.edukacija.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        if(userRepository.usernameExists(user.getUsername())) {
            throw new UsernameAlreadyExistsException("User with that username already exists.");
        }
        else if(!user.getName().contains(" ")) {
            throw new UsernameMustContainSpaceException("Name must contain a space between name and surname.");
        }
        else return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User tryLogin(UserLoginModel user) {
        return userRepository.tryLogin(
                user.getUsername(),
                user.getPassword()
        ).orElseThrow(() -> new InvalidLoginDataException("Invalid Username or password"));
    }

    public User reset(UserResetModel model) {
        Optional<User> u = userRepository.tryLogin(model.getUsername(),model.getPassword());
        if(u.isPresent()) {
            User user = u.get();
            user.setPassword(model.getNewPassword());
            return userRepository.save(user);
        }
        throw new InvalidLoginDataException("Invalid Username or password");
    }
}
