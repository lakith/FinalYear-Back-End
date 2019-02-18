package com.finalproj.finalproject.model;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_special_guests")
    private EventSpecialGuests eventSpecialGuests;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_genaral_guests")
    private EventGenaralGuests eventGenaralGuests;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_front_page")
    private EventFrontPage eventFrontPage;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_comments")
    private List<EventComments> eventComments;



}
