package com.finalproj.finalproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "event_genaral_guests")
public class EventGenaralGuests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventGeneralGuestsId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_guest")
    private List<GeneralGuest> generalGuest;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_guest_message")
    private List<GenaralGuestMessages> generalGuestMessages;

    public EventGenaralGuests() {
    }

    public int getEventGeneralGuestsId() {
        return eventGeneralGuestsId;
    }

    public void setEventGeneralGuestsId(int eventGeneralGuestsId) {
        this.eventGeneralGuestsId = eventGeneralGuestsId;
    }

    public List<GeneralGuest> getGeneralGuest() {
        return generalGuest;
    }

    public void setGeneralGuest(List<GeneralGuest> generalGuest) {
        this.generalGuest = generalGuest;
    }

    public List<GenaralGuestMessages> getGeneralGuestMessages() {
        return generalGuestMessages;
    }

    public void setGeneralGuestMessages(List<GenaralGuestMessages> generalGuestMessages) {
        this.generalGuestMessages = generalGuestMessages;
    }
}
