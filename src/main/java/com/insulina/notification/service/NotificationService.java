package com.insulina.notification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.insulina.notification.Events.ProjectEvent;
import com.insulina.notification.email.EmailSender;
import com.insulina.notification.enums.NotificationChannelEnum;
import com.insulina.notification.enums.NotificationStatusEnum;
import com.insulina.notification.model.Notification;
import com.insulina.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());
    private final EmailSender emailSender;
    private final NotificationRepository notificationRepository;

    public void processEvent(ProjectEvent event) {
        List<String> sendTo = event.getSendTo();
        String subject = event.getEventType().getSubject();
        String body = new StringBuilder("Se le informa que el proyecto: ")
                .append(event.getProjectName())
                .append(" " + event.getEventType().getDescription()).toString();
        Long projectId = event.getProjectId();
        String projectName = event.getProjectName();
        String fromEmail = System.getenv("SPRING_MAIL_USERNAME");

        sendTo.forEach(email -> {
            boolean sent = false;
            NotificationStatusEnum status = NotificationStatusEnum.FAILED;
            String errorMessage = null;

            try {
                emailSender.send(email, subject, body);
                sent = true;
                status =  NotificationStatusEnum.SENT;
                LOGGER.info("Email enviado exitosamente a: " + email);
            } catch (Exception ex) {
                errorMessage = ex.getMessage();
                LOGGER.severe("Error enviando email a " + email + ": " + errorMessage);
            }

            // Guardar notificación con el estado real
            try {
                Notification notification = createNotification(
                        email, subject, body, fromEmail,
                        projectId, projectName, status, errorMessage);
                notificationRepository.save(notification);
            } catch (Exception ex) {
                LOGGER.severe("Error guardando notificación para " + email + ": " + ex.getMessage());
            }
        });
    }

    private Notification createNotification(String toEmail, String subject, String body, String fromEmail,
            Long projectId, String projectName, NotificationStatusEnum status, String errorMessage) {
        return new Notification().builder()
                .projectId(projectId)
                .projectName(projectName)
                .sendTo(toEmail)
                .sendFrom(fromEmail)
                .subject(subject)
                .body(body)
                .channel(NotificationChannelEnum.EMAIL)
                .sendAt(LocalDateTime.now())
                .status(status)
                .errorMessage(errorMessage)
                .build();
    }

    public List<Notification> getAllLogs() {
        return notificationRepository.findAll();
    }

    public List<Notification> getLogsByProjectName(String projectName) {
        return notificationRepository.findByProjectName(projectName);
    }

    public List<Notification> getLogsBySendTo(String sendTo) {
        return notificationRepository.findBySendTo(sendTo);
    }

    public List<Notification> getLogsByProjectNameAndSendTo(String projectName, String sendTo) {
        return notificationRepository.findByProjectNameAndSendTo(projectName, sendTo);
    }

}
