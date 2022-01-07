package com.higroup.Buda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String sender;

    @Async
    public void send(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setText(content, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(sender);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to send email");
        }
    }
}
