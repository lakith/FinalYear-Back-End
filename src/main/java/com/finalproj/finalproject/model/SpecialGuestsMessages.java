package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "special_guests_messages")
public class SpecialGuestsMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specialGuestMessagesId;

    private String message;

    public SpecialGuestsMessages() {
    }

    public int getSpecialGuestMessagesId() {
        return specialGuestMessagesId;
    }

    public void setSpecialGuestMessagesId(int specialGuestMessagesId) {
        this.specialGuestMessagesId = specialGuestMessagesId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
