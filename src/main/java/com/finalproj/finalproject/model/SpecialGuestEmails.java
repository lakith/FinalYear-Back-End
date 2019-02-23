package com.finalproj.finalproject.model;

import org.springframework.http.ResponseEntity;

import javax.persistence.*;

@Entity
@Table(name = "special_guest_emails")
public class SpecialGuestEmails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specialGuestEmailId;

    private String mail;

    public SpecialGuestEmails() {
    }

    public int getSpecialGuestEmailId() {
        return specialGuestEmailId;
    }

    public void setSpecialGuestEmailId(int specialGuestEmailId) {
        this.specialGuestEmailId = specialGuestEmailId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
