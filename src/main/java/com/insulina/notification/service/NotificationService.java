package com.insulina.notification.service;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.insulina.notification.email.EmailSender;

@Service
public class NotificationService {

    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());
    private final EmailSender emailSender;

    public NotificationService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void processEvent(Map<String, Object> event) {
        // Lógica para procesar el evento de notificación
        LOGGER.info("Procesando evento: " + event);
        String toEmail = (String) event.get("toEmail");
        String subject = (String) event.get("subject");
        String body = (String) event.get("body");

        try {
            emailSender.send(toEmail, subject, body);
        } catch (Exception e) {
            LOGGER.severe("Error al enviar notificación: " + e.getMessage());
        }
    }
    
}
