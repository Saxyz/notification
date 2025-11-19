package com.insulina.notification.model;

import java.time.LocalDateTime;

import com.insulina.notification.enums.NotificationChannelEnum;
import com.insulina.notification.enums.NotificationStatusEnum;

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
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "project_name")
    private String projectName;
    @Column(name="send_to")
    private String sendTo;
    @Column(name="send_from")
    private String sendFrom;
    private String subject;
    private String body;
    private NotificationChannelEnum channel;
    @Column(name = "send_at")
    private LocalDateTime sendAt;
    private NotificationStatusEnum status;
    private String errorMessage;
}
