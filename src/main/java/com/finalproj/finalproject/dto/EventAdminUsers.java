package com.finalproj.finalproject.dto;

import java.util.List;

public class EventAdminUsers {

    private int eventId;

    private List<Integer> adminUsers;

    public EventAdminUsers() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public List<Integer> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<Integer> adminUsers) {
        this.adminUsers = adminUsers;
    }
}
