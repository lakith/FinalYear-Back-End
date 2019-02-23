package com.finalproj.finalproject.dto;

import javax.validation.constraints.NotNull;

public class CommentUpdateDto {

    private int commentId;

    @NotNull
    private String comment;

    public CommentUpdateDto() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
