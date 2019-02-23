package com.finalproj.finalproject.model;

import com.finalproj.finalproject.Enums.UserConfirmation;

import javax.persistence.*;

@Entity
@Table(name = "genaral_guest_messages")
public class GenaralGuestMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genaralGuestMessage;

    private String message;


    public GenaralGuestMessages() {
    }

    public int getGenaralGuestMessage() {
        return genaralGuestMessage;
    }

    public void setGenaralGuestMessage(int genaralGuestMessage) {
        this.genaralGuestMessage = genaralGuestMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
