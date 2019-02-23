package com.finalproj.finalproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "event_special_guests")
public class EventSpecialGuests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventSpecialGuestId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_guest")
    private List<SpecialGuest> specialGuest;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_guests_messages")
    private  List<SpecialGuestsMessages> specialGuestsMessages;

    public EventSpecialGuests() {
    }

    public int getEventSpecialGuestId() {
        return eventSpecialGuestId;
    }

    public void setEventSpecialGuestId(int eventSpecialGuestId) {
        this.eventSpecialGuestId = eventSpecialGuestId;
    }

    public List<SpecialGuest> getSpecialGuest() {
        return specialGuest;
    }

    public void setSpecialGuest(List<SpecialGuest> specialGuest) {
        this.specialGuest = specialGuest;
    }

    public List<SpecialGuestsMessages> getSpecialGuestsMessages() {
        return specialGuestsMessages;
    }

    public void setSpecialGuestsMessages(List<SpecialGuestsMessages> specialGuestsMessages) {
        this.specialGuestsMessages = specialGuestsMessages;
    }
}
