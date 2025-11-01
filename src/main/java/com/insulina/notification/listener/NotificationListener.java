package com.insulina.notification.listener;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.insulina.notification.Events.ProjectEvent;
import com.insulina.notification.service.NotificationService;

@Component
public class NotificationListener {
    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "notifications.email.queue")
    public void onEvent(ProjectEvent event) {
        try {
            System.out.println("Listener recibió evento del proyecto: " + event.getProjectName() + " De tipo: " + event.getEventType().getCode());
            notificationService.processEvent(event);
        } catch (Exception ex) {
            // Si hay excepción, dejar que Spring reintente según configuración.
            System.err.println("Error procesando evento: " + ex.getMessage());
            throw ex;
        }
    }
}
