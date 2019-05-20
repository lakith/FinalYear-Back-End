package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "event_pement")
public class EventPeyment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventPeymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peyment_package_event_id")
    private PeymantPackage peymantPackage;

    public EventPeyment() {
    }

    public int getEventPeymentId() {
        return eventPeymentId;
    }

    public void setEventPeymentId(int eventPeymentId) {
        this.eventPeymentId = eventPeymentId;
    }

    public PeymantPackage getPeymantPackage() {
        return peymantPackage;
    }

    public void setPeymantPackage(PeymantPackage peymantPackage) {
        this.peymantPackage = peymantPackage;
    }
}
