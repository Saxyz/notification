package com.insulina.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insulina.notification.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
