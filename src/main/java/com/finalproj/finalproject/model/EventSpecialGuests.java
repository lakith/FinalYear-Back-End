package com.finalproj.finalproject.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class EventSpecialGuests {

    private int eventSpecialGuestId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_user")
    private User specialUser;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_guests_messages")
    private  SpecialGuestsMessages specialGuestsMessages;




}
