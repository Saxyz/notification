package com.insulina.notification.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    //con plantilla html sencilla
    public void send(String toEmail, String subject, String body) throws Exception {
        if (toEmail == null || toEmail.isBlank()) {
            throw new IllegalArgumentException("Destinatario inválido");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("notificaciones@tuapp.com"); // cambia a un dominio tuyo

        try {
            mailSender.send(message);
            System.out.printf("Email enviado a %s con asunto '%s'%n", toEmail, subject);
        } catch (Exception e) {
            System.err.printf("Error enviando correo a %s: %s%n", toEmail, e.getMessage());
            throw e;
        }
    }
}