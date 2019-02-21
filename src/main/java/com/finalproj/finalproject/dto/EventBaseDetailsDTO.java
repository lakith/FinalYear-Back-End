package com.finalproj.finalproject.dto;

import java.util.Date;

public class EventBaseDetailsDTO {

    private String eventHeading;

    private Date eventStartDate;

    private Date eventEndDate;

    private String eventPlace;

    private String eventHostedUrl;

    private int eventTypeId;

    public EventBaseDetailsDTO() {
    }

    public String getEventHeading() {
        return eventHeading;
    }

    public void setEventHeading(String eventHeading) {
        this.eventHeading = eventHeading;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getEventHostedUrl() {
        return eventHostedUrl;
    }

    public void setEventHostedUrl(String eventHostedUrl) {
        this.eventHostedUrl = eventHostedUrl;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }
}
