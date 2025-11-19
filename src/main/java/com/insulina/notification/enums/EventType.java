package com.insulina.notification.enums;

public enum EventType {
    PROJECT_CREATED("Proyecto Creado", "Ha sido creado"),
    PROJECT_VOTATION_OPEN("Votación Abierta","Ha sido abierto a votación"),
    PROJECT_VOTATION_CLOSED("Votación Cerrada", "Ha cerrado su votación"),
    PROJECT_IN_REVISION("Proyecto en Revisión", "Se encuentra en revisión");

    private final String subject;
    private final String description;

    private EventType(String subject, String description) {
        this.subject = subject;
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return this.name();
    }
}
