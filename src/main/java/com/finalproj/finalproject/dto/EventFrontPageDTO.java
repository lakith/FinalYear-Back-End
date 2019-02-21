package com.finalproj.finalproject.dto;

import org.springframework.web.multipart.MultipartFile;

public class EventFrontPageDTO {


    private String content;

    private String termsAndConditions;

    private String otherDetails;

    private int eventID;

    private MultipartFile frontImage;

    public EventFrontPageDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public MultipartFile getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(MultipartFile frontImage) {
        this.frontImage = frontImage;
    }
}
