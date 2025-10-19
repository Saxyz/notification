package com.insulina.notification.listener;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.insulina.notification.service.NotificationService;

@Component
public class NotificationListener {
    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "notifications.email.queue")
    public void onEvent(Map<String, Object> event) {
        try {
            System.out.println("Listener recibió evento: " + event.get("eventType") + " id=" + event.get("eventId"));
            notificationService.processEvent(event);
        } catch (Exception ex) {
            // Si hay excepción, dejar que Spring reintente según configuración.
            System.err.println("Error procesando evento: " + ex.getMessage());
            throw ex;
        }
    }
}
