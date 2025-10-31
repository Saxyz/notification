package com.insulina.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insulina.notification.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
    List<Notification> findByProjectName(String projectName);
    List<Notification> findBySendTo(String sendTo);
    List<Notification> findByProjectNameAndSendTo(String projectName, String sendTo);
}
