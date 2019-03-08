package com.finalproj.finalproject.dto;

import com.finalproj.finalproject.model.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class EventDisplayDTO {


    private int eventId;

    private List<User> eventCreators;

    private EventType eventType;

    private String eventName;

    private String eventThumbnail;

    private Date eventStartDate;

    private Date eventEndDate;

    private String eventPlace;

    private String eventHostedUrl;

    private int numberOfGuests;

    private boolean paidEvent = false;

    private PaidEvent paidEventData;

    private boolean freeEvent = true;

    private boolean eventPrivate = false;

    private boolean eventPublic = false;

    private EventSpecialGuests eventSpecialGuests;

    private List<SpecialGuestEmails> specialGuestEmails;

    private List<GenaralGuestMails> genaralGuestMails;

    private EventGenaralGuests eventGenaralGuests;

    private EventFrontPage eventFrontPage;

    private List<EventComments> eventComments;

    private boolean closed = false;


    public EventDisplayDTO() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public List<User> getEventCreators() {
        return eventCreators;
    }

    public void setEventCreators(List<User> eventCreators) {
        this.eventCreators = eventCreators;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventThumbnail() {
        return eventThumbnail;
    }

    public void setEventThumbnail(String eventThumbnail) {
        this.eventThumbnail = eventThumbnail;
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

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public boolean isPaidEvent() {
        return paidEvent;
    }

    public void setPaidEvent(boolean paidEvent) {
        this.paidEvent = paidEvent;
    }

    public PaidEvent getPaidEventData() {
        return paidEventData;
    }

    public void setPaidEventData(PaidEvent paidEventData) {
        this.paidEventData = paidEventData;
    }

    public boolean isFreeEvent() {
        return freeEvent;
    }

    public void setFreeEvent(boolean freeEvent) {
        this.freeEvent = freeEvent;
    }

    public boolean isEventPrivate() {
        return eventPrivate;
    }

    public void setEventPrivate(boolean eventPrivate) {
        this.eventPrivate = eventPrivate;
    }

    public boolean isEventPublic() {
        return eventPublic;
    }

    public void setEventPublic(boolean eventPublic) {
        this.eventPublic = eventPublic;
    }

    public EventSpecialGuests getEventSpecialGuests() {
        return eventSpecialGuests;
    }

    public void setEventSpecialGuests(EventSpecialGuests eventSpecialGuests) {
        this.eventSpecialGuests = eventSpecialGuests;
    }

    public List<SpecialGuestEmails> getSpecialGuestEmails() {
        return specialGuestEmails;
    }

    public void setSpecialGuestEmails(List<SpecialGuestEmails> specialGuestEmails) {
        this.specialGuestEmails = specialGuestEmails;
    }

    public List<GenaralGuestMails> getGenaralGuestMails() {
        return genaralGuestMails;
    }

    public void setGenaralGuestMails(List<GenaralGuestMails> genaralGuestMails) {
        this.genaralGuestMails = genaralGuestMails;
    }

    public EventGenaralGuests getEventGenaralGuests() {
        return eventGenaralGuests;
    }

    public void setEventGenaralGuests(EventGenaralGuests eventGenaralGuests) {
        this.eventGenaralGuests = eventGenaralGuests;
    }

    public EventFrontPage getEventFrontPage() {
        return eventFrontPage;
    }

    public void setEventFrontPage(EventFrontPage eventFrontPage) {
        this.eventFrontPage = eventFrontPage;
    }

    public List<EventComments> getEventComments() {
        return eventComments;
    }

    public void setEventComments(List<EventComments> eventComments) {
        this.eventComments = eventComments;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
