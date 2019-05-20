package com.finalproj.finalproject.dto;

import java.util.Date;
import java.util.List;

public class EventSubEventDTO {

    private String subEventName;

    private Date eventDate;

    private String time;

    private List<SubEventsGuestsDTO> subEventsGuestsDTOS;

    public EventSubEventDTO() {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<SubEventsGuestsDTO> getSubEventsGuestsDTOS() {
        return subEventsGuestsDTOS;
    }

    public void setSubEventsGuestsDTOS(List<SubEventsGuestsDTO> subEventsGuestsDTOS) {
        this.subEventsGuestsDTOS = subEventsGuestsDTOS;
    }
}
