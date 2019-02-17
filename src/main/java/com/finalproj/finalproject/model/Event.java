package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventId;

    @OneToOne
    @JoinColumn(name = "event_type")
    private EventType eventType;

    private String eventName;

    private String eventStartDate;

    private String eventEndDate;

    private String eventPlace;

    private String eventHostedUrl;

    private int numberOfGuests;

    private boolean paidEvent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchId")
    private PaidEvent paidEventData;

    private boolean freeEvent;

    private boolean eventPrivate;

    private boolean eventPublic;







}
