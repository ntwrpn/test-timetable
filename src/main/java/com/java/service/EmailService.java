
package com.java.service;

import com.java.domain.Users;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {
    SimpleMailMessage constructResetTokenEmail(String token, Users user);
    SimpleMailMessage constructVerificationTokenEmail(String token, Users user);
    SimpleMailMessage constructEmail(String subject, String body, Users user);
}
