package com.finalproj.finalproject.dto;

import javax.validation.constraints.NotNull;

public class UserConfirmationDTO {

    @NotNull
    private int eventId;
    @NotNull
    private int mealPreference;
    @NotNull
    private int confirmation;

    public UserConfirmationDTO() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getMealPreference() {
        return mealPreference;
    }

    public void setMealPreference(int mealPreference) {
        this.mealPreference = mealPreference;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }
}
