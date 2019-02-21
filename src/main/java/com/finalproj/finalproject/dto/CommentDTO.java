package com.finalproj.finalproject.dto;

public class CommentDTO {

    private int eventId;

    private String comment;

    public CommentDTO() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
