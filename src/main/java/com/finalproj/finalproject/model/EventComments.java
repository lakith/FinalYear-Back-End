package com.finalproj.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "event_comments")
public class EventComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventCommentId;

    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commenter")
    private User commenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_event")
    @JsonIgnore
    private Event event;

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

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
