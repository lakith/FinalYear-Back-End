package com.finalproj.finalproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "event_form")
public class EventForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventFormId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_form_event_id")
    private Event event;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "form_config_event_form")
    private List<FormConfig> formConfigs;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_form_form_data")
    private List<FormData> formData;

    public EventForm() {
    }

    public int getEventFormId() {
        return eventFormId;
    }

    public void setEventFormId(int eventFormId) {
        this.eventFormId = eventFormId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<FormConfig> getFormConfigs() {
        return formConfigs;
    }

    public void setFormConfigs(List<FormConfig> formConfigs) {
        this.formConfigs = formConfigs;
    }

    public List<FormData> getFormData() {
        return formData;
    }

    public void setFormData(List<FormData> formData) {
        this.formData = formData;
    }
}
