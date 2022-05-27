package com.example.covoitonsapi.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
@Slf4j
public class EmailService implements IEmailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    public void send(String to, String email) {
        log.info("laaaa" + System.getenv("SENDGRID_API_KEY"));
        Email from = new Email("covoitons@gmail.com");
        String subject = "Confirmation du compte Covoitons";
        Email setTo = new Email(to);
        Content content = new Content("text/html", email);
        Mail mail = new Mail(from, subject, setTo, content);

        SendGrid sendGrid = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGrid.api(request);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
