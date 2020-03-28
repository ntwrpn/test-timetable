

package com.java.service.impl;

import com.java.domain.Users;
import com.java.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public SimpleMailMessage constructResetTokenEmail(String token, Users user) {
        String url = "http://localhost:8080/changePassword?id=" +
                user.getId() + "&token=" + token;
        String message = "To reset your password, click this link: \n";
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    @Override
    public SimpleMailMessage constructVerificationTokenEmail(String token, Users user) {
        String url = "http://localhost:8080/verifyEmail?token=" + token;
        String message = "To complete your account set-up, click this link: \n";
        return constructEmail("Verify Email", message + " \r\n" + url, user);
    }

    @Override
    public SimpleMailMessage constructEmail(String subject, String body,
                                             Users user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getUsername());
        email.setFrom("bntu-timetable@mail.ru");
        javaMailSender.send(email);
        return email;
    }

}
