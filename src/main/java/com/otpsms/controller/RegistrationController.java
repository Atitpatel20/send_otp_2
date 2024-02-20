package com.otpsms.controller;

import com.otpsms.entity.User;
import com.otpsms.service.EmailService;
import com.otpsms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;
    @PostMapping("/register")
    public Map<String,String>registerUser(@RequestBody User user){
        User registerUser= userService.registerUser(user);
        emailService.sendOtpEmail(user.getEmail());
        return null;
    }
}
