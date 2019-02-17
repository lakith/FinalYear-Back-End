package com.finalproj.finalproject.model;

import com.finalproj.finalproject.Enums.UserConfirmation;

import javax.persistence.*;

@Entity
@Table(name = "special_guest")
public class SpecialGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int specialGuestId;

    private User specialUser;

    @Enumerated(EnumType.STRING)
    private UserConfirmation confirmation;

    public SpecialGuest() {
    }

    public int getSpecialGuestId() {
        return specialGuestId;
    }

    public void setSpecialGuestId(int specialGuestId) {
        this.specialGuestId = specialGuestId;
    }

    public User getSpecialUser() {
        return specialUser;
    }

    public void setSpecialUser(User specialUser) {
        this.specialUser = specialUser;
    }

    public UserConfirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(UserConfirmation confirmation) {
        this.confirmation = confirmation;
    }
}
