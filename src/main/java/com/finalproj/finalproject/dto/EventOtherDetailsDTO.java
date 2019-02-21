package com.finalproj.finalproject.dto;

public class EventOtherDetailsDTO {

    private int eventId;

    private int maximumNumberOfGuests;

    private int eventCategoryId;

    private int eventGroupId;

    public EventOtherDetailsDTO() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getMaximumNumberOfGuests() {
        return maximumNumberOfGuests;
    }

    public void setMaximumNumberOfGuests(int maximumNumberOfGuests) {
        this.maximumNumberOfGuests = maximumNumberOfGuests;
    }

    public int getEventCategoryId() {
        return eventCategoryId;
    }

    public void setEventCategoryId(int eventCategoryId) {
        this.eventCategoryId = eventCategoryId;
    }

    public int getEventGroupId() {
        return eventGroupId;
    }

    public void setEventGroupId(int eventGroupId) {
        this.eventGroupId = eventGroupId;
    }
}
