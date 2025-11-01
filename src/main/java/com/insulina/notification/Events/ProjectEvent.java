package com.insulina.notification.events;

import java.util.List;

import com.insulina.notification.enums.EventType;

import lombok.Data;

@Data
public class ProjectEvent {
    private List<String> sendTo;
    private Long projectId;
    private String projectName;
    private EventType eventType;
}
