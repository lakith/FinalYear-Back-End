package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "event_comments")
public class EventComments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventCommentId;

    private String comment;

    public EventComments() {
    }

    public int getEventCommentId() {
        return eventCommentId;
    }

    public void setEventCommentId(int eventCommentId) {
        this.eventCommentId = eventCommentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
