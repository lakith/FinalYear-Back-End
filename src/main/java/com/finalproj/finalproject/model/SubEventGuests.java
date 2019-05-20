package com.finalproj.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "sub_event_guests")
public class SubEventGuests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subEventGuestsId;

    private String guestName;

    private String guestEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_sub_event_guests")
    @JsonIgnore
    private EventSubEvents eventSubEvents;

    public SubEventGuests() {
    }

    public int getSubEventGuestsId() {
        return subEventGuestsId;
    }

    public void setSubEventGuestsId(int subEventGuestsId) {
        this.subEventGuestsId = subEventGuestsId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public EventSubEvents getEventSubEvents() {
        return eventSubEvents;
    }

    public void setEventSubEvents(EventSubEvents eventSubEvents) {
        this.eventSubEvents = eventSubEvents;
    }
}
