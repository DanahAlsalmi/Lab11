package com.example.lab11.Controller;

import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //Get
    @GetMapping("/get")
    public ResponseEntity getAllUser() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    //Update
    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(userId, user);
        return ResponseEntity.status(200).body("User updated successfully");
    }

    //Delete
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

    //get by username and password
    @GetMapping("/username-password/{username}/{password}")
    public ResponseEntity getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        User user = userService.findUsernameAndPassword(username, password);
        return ResponseEntity.status(200).body("User correctly found:" + user);
    }

    // by Email
    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        return ResponseEntity.status(200).body("User correctly found :" + user);
    }
}
