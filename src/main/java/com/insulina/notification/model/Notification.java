package com.insulina.notification.model;

import java.time.LocalDateTime;

import com.insulina.notification.enums.NotificationChannelEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="to_email")
    private String toEmail;
    @Column(name="from_email")
    private String fromEmail;
    private String subject;
    private String body;
    private NotificationChannelEnum channel;
    @Column(name = "send_at")
    private LocalDateTime sendAt;
}
