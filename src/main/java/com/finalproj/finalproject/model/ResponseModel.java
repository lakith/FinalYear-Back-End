package com.finalproj.finalproject.model;

public class ResponseModel {

    private String message;
    private String error;
    private boolean status;

    public ResponseModel() {
    }

    public ResponseModel(String message, String error, boolean status) {
        this.message = message;
        this.error = error;
        this.status = status;
    }

    public ResponseModel(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
