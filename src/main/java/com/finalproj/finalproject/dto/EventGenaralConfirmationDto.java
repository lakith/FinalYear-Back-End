package com.finalproj.finalproject.dto;

import java.util.List;

public class EventGenaralConfirmationDto {

    private int eventId;
    private List<Integer> GenaralGuestEmails;

    public EventGenaralConfirmationDto() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public List<Integer> getGenaralGuestEmails() {
        return GenaralGuestEmails;
    }

    public void setGenaralGuestEmails(List<Integer> genaralGuestEmails) {
        GenaralGuestEmails = genaralGuestEmails;
    }
}
