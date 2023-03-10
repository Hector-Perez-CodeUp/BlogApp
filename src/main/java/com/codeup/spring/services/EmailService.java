package com.codeup.spring.services;
// Imports
import com.codeup.spring.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
// Fields
    @Autowired
    public JavaMailSender emailSender;

    // Grabbing Sender Info
    @Value("${spring.mail.from}")
    private String from;

    public void prepareAndSend(Post post, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(post.getUser().getEmail());
        message.setSubject(subject);
        message.setText(body);

        try {
            this.emailSender.send(message);
        } catch (MailException e) {
            System.err.println("There was an error sending email: " + e.getMessage());
        }
    }

}
