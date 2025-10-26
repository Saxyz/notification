package com.insulina.notification.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.insulina.notification.email.EmailSender;
import com.insulina.notification.enums.NotificationChannelEnum;
import com.insulina.notification.model.Notification;
import com.insulina.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());
    private final EmailSender emailSender;
    private final NotificationRepository notificationRepository;

    public void processEvent(Map<String, Object> event) {
        // Lógica para procesar el evento de notificación
        LOGGER.info("Procesando evento: " + event);
        String toEmail = (String) event.get("toEmail");
        String subject = (String) event.get("subject");
        String body = (String) event.get("body");
        String fromEmail = System.getenv("SPRING_MAIL_USERNAME");

        Notification notification = createNotification(toEmail, subject, body, fromEmail);

        notificationRepository.save(notification);

        try {
            emailSender.send(toEmail, subject, body);
        } catch (Exception e) {
            LOGGER.severe("Error al enviar notificación: " + e.getMessage());
        }
    }

    private Notification createNotification(String toEmail, String subject, String body, String fromEmail) {
        return new Notification().builder()
        .toEmail(toEmail)
        .fromEmail(fromEmail)
        .subject(subject)
        .body(body)
        .channel(NotificationChannelEnum.EMAIL)
        .sendAt(LocalDateTime.now())
        .build();
    }
    
}
