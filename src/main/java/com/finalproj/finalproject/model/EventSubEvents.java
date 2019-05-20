package com.finalproj.finalproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event_sub_events")
public class EventSubEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventSubEventsId;

    @NotNull
    private String subEventName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date eventDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_sub_event_guests")
    private List<SubEventGuests> subEventGuests;

    private String startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_event_sub")
    @JsonIgnore
    private Event event;

    public EventSubEvents() {
    }

    public int getEventSubEventsId() {
        return eventSubEventsId;
    }

    public void setEventSubEventsId(int eventSubEventsId) {
        this.eventSubEventsId = eventSubEventsId;
    }

    public String getSubEventName() {
        return subEventName;
    }

    public void setSubEventName(String subEventName) {
        this.subEventName = subEventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public List<SubEventGuests> getSubEventGuests() {
        return subEventGuests;
    }

    public void setSubEventGuests(List<SubEventGuests> subEventGuests) {
        this.subEventGuests = subEventGuests;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
