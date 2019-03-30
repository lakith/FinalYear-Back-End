package com.finalproj.finalproject.dto;

public class FrontPageDTO {

    private int eventFrontPageId;

    private String topImage;

    private String discription;

    private String termsAndConditions;

    private String otherDetails;

    public FrontPageDTO() {
    }

    public int getEventFrontPageId() {
        return eventFrontPageId;
    }

    public void setEventFrontPageId(int eventFrontPageId) {
        this.eventFrontPageId = eventFrontPageId;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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
}
