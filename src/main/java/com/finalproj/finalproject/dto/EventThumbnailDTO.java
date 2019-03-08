package com.finalproj.finalproject.dto;

import org.springframework.web.multipart.MultipartFile;

public class EventThumbnailDTO {

    private int eventId;

    private MultipartFile file;

    public EventThumbnailDTO() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
