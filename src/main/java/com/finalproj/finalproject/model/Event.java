package com.finalproj.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_creator")
    private List<User> eventCreators;

    @OneToOne
    @JoinColumn(name = "event_type")
    private EventType eventType;

    private String eventName;

    private String eventThumbnail;

    private Date eventStartDate;

    private Date eventEndDate;

    private String eventPlace;

    private String eventHostedUrl;

    private int numberOfGuests;

    private boolean paidEvent = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchId")
    private PaidEvent paidEventData;

    private boolean freeEvent = true;

    private boolean eventPrivate = false;

    private boolean eventPublic = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_special_guests")
    private EventSpecialGuests eventSpecialGuests;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_guest_mail")
    private List<SpecialGuestEmails> specialGuestEmails;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_guest_mail")
    private List<GenaralGuestMails> genaralGuestMails;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_genaral_guests")
    private EventGenaralGuests eventGenaralGuests;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_front_page")
    private EventFrontPage eventFrontPage;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_comments")
    private List<EventComments> eventComments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_form")
    @JsonIgnore
    private EventForm eventForm;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_event_sub")
    private List<EventSubEvents> eventSubEvents;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_payment_id")
    private EventPeyment eventPeyment;

    private boolean eventPublishToDisplay;

    public Event() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public List<User> getEventCreators() {
        return eventCreators;
    }

    public void setEventCreators(List<User> eventCreators) {
        this.eventCreators = eventCreators;
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

    public String getEventThumbnail() {
        return eventThumbnail;
    }

    public void setEventThumbnail(String eventThumbnail) {
        this.eventThumbnail = eventThumbnail;
    }

    public EventForm getEventForm() {
        return eventForm;
    }

    public void setEventForm(EventForm eventForm) {
        this.eventForm = eventForm;
    }

    public List<EventSubEvents> getEventSubEvents() {
        return eventSubEvents;
    }

    public void setEventSubEvents(List<EventSubEvents> eventSubEvents) {
        this.eventSubEvents = eventSubEvents;
    }

    public EventPeyment getEventPeyment() {
        return eventPeyment;
    }

    public void setEventPeyment(EventPeyment eventPeyment) {
        this.eventPeyment = eventPeyment;
    }

    public boolean isEventPublishToDisplay() {
        return eventPublishToDisplay;
    }

    public void setEventPublishToDisplay(boolean eventPublishToDisplay) {
        this.eventPublishToDisplay = eventPublishToDisplay;
    }
}
