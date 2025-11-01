package com.insulina.notification.Events;

import java.util.List;

import lombok.Data;

@Data
public class ProjectEvent {
    private List<String> sendTo;
    private Long projectId;
    private String projectName;
    private EventType eventType;
}
