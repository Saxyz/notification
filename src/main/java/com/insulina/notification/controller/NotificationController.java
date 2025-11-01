package com.insulina.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insulina.notification.model.Notification;
import com.insulina.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("notifications/logs")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private final NotificationService service;

    @GetMapping
    public List<Notification> getLogsByFilters(
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) String sendTo) {

        if (projectName != null && sendTo != null) {
            return service.getLogsByProjectNameAndSendTo(projectName, sendTo);
        } else if (projectName != null) {
            return service.getLogsByProjectName(projectName);
        } else if (sendTo != null) {
            return service.getLogsBySendTo(sendTo);
        } else {
            return service.getAllLogs(); // o lanzar una excepción
        }
    }
}
