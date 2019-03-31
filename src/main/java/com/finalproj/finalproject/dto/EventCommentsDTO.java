package com.finalproj.finalproject.dto;

public class EventCommentsDTO {

    private int eventCommentId;

    private String comment;

    private String commenterName;

    private String commenterProfile;

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

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommenterProfile() {
        return commenterProfile;
    }

    public void setCommenterProfile(String commenterProfile) {
        this.commenterProfile = commenterProfile;
    }
}
