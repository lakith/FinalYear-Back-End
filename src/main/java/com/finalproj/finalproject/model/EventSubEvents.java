package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "event_sub_events")
public class EventSubEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventSubEventsId;

    private String eventName;

    private String eventDate;
}
