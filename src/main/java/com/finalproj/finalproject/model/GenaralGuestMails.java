package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "general_guest")
public class GenaralGuestMails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int generalGuestId;

    private String mail;

    public GenaralGuestMails() {
    }

    public int getGeneralGuestId() {
        return generalGuestId;
    }

    public void setGeneralGuestId(int generalGuestId) {
        this.generalGuestId = generalGuestId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
