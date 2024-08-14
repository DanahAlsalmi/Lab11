package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //Get All
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Add
    public User addUser(User user) {
        user.setRegistrationDate(LocalDate.now());
        return userRepository.save(user);
    }

    //Update
    public void updateUser(Integer userId, User user) {
        User u = userRepository.findUserByUserId(userId);
        if (u == null) {
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        userRepository.save(u);
    }

    //Delete
    public void deleteUser(Integer userId) {
        User u = userRepository.findUserByUserId(userId);
        if (u == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }

    //username and password
    public User findUsernameAndPassword(String username, String password) {
        User u = userRepository.findUserByUsernameAndPassword(username, password);
        if (u == null) {
            throw new ApiException("incorrect username or password");
        }
        return u;
    }

    //Email
    public User findUserByEmail(String email) {
        User u = userRepository.findUserByEmail(email);
        if (u == null) {
            throw new ApiException("the user doesn't exist");
        }
        return u;
    }
}
