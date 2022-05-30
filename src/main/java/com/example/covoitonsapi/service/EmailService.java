package com.example.covoitonsapi.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class EmailService implements IEmailService {
    @Value("${spring.sendgrid.api-key}")
    String apiKey;

    public void send(String to, String email) {
        Email from = new Email("covoitons@gmail.com");
        String subject = "Confirmation du compte Covoitons";
        Email setTo = new Email(to);
        Content content = new Content("text/html", email);
        Mail mail = new Mail(from, subject, setTo, content);

        SendGrid sendGrid = new SendGrid(apiKey);
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
