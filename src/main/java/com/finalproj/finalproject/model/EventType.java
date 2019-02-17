package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "event_Types")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventTypeId;

    private String eventTypeName;


    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
}
