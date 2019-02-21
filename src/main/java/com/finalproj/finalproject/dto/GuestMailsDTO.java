package com.finalproj.finalproject.dto;

import java.util.List;

public class GuestMailsDTO {
    private int eventId;
    private List<String> emails;

    public GuestMailsDTO() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
