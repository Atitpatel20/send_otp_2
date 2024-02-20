package com.otpsms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.otpsms.service.EmailVerificationService.emailOtpMapping;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private final UserService userService;

    public EmailService(UserService userService) {
        this.userService = userService;
    }

    public String generateOtp() {
        return String.format("%06d", new java.util.Random().nextInt(1000000));
    }

    public Map<String, String> sendOtpEmail(String email) {
        String otp = generateOtp();
        // save otp for later verification
        emailOtpMapping.put(email, otp);
        sendEmail(email, "OTP for Email Verification", "Your OTP is: " + otp);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "OTP sent successfully");
        return response;
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your.gmail@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }


}
