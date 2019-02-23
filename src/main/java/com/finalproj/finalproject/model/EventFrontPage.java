package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "event_front_page")
public class EventFrontPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventFrontPageId;

    private String topImage;

    private String content;

    private String termsAndConditions;

    private String otherDetails;

    public EventFrontPage() {
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
}
