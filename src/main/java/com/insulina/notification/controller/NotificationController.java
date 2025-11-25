package com.insulina.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.insulina.notification.model.Notification;
import com.insulina.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("notifications/logs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {

    @Autowired
    private final NotificationService service;

    @GetMapping
    public List<Notification> getNotificationsLogsByFilters(
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) String sendTo) {

        if (projectName != null && sendTo != null) {
            return service.getNotificationsLogsByProjectNameAndSendTo(projectName, sendTo);
        } else if (projectName != null) {
            return service.getNotificationsLogsByProjectName(projectName);
        } else if (sendTo != null) {
            return service.getNotificationsLogsBySendTo(sendTo);
        } else {
            return service.getAllNotificationsLogs(); // o lanzar una excepción
        }
    }
}
