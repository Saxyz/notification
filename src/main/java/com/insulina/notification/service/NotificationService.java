package com.insulina.notification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.insulina.notification.email.EmailSender;
import com.insulina.notification.enums.NotificationChannelEnum;
import com.insulina.notification.model.Notification;
import com.insulina.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

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
        Long projectId = (Long) event.get("projectId");
        String projectName = (String) event.get("projectName");
        String fromEmail = System.getenv("SPRING_MAIL_USERNAME");

        Notification notification = createNotification(toEmail, subject, body, fromEmail, projectId, projectName);

        try {
            emailSender.send(toEmail, subject, body);
            notificationRepository.save(notification);
        } catch (Exception e) {
            LOGGER.severe("Error al enviar notificación: " + e.getMessage());
        }
    }

    private Notification createNotification(String toEmail, String subject, String body, String fromEmail, Long projectId, String projectName) {
        return new Notification().builder()
        .projectId(projectId)
        .projectName(projectName)
        .sendTo(toEmail)
        .sendFrom(fromEmail)
        .subject(subject)
        .body(body)
        .channel(NotificationChannelEnum.EMAIL)
        .sendAt(LocalDateTime.now())
        .build();
    }

    public List<Notification> getAllLogs(){
        return notificationRepository.findAll();
    }

    public List<Notification> getLogsByProjectName(String projectName){
        return notificationRepository.findByProjectName(projectName);
    }

    public List<Notification> getLogsBySendTo(String sendTo){
        return notificationRepository.findBySendTo(sendTo);
    }

    public List<Notification> getLogsByProjectNameAndSendTo(String projectName, String sendTo){
        return notificationRepository.findByProjectNameAndSendTo(projectName, sendTo);
    }
    
}
