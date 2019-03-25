package com.finalproj.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "form_data")
public class FormData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int formDataId;

    private String data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_form_form_data")
    @JsonIgnore
    private EventForm eventForm;

    public FormData() {
    }

    public int getFormDataId() {
        return formDataId;
    }

    public void setFormDataId(int formDataId) {
        this.formDataId = formDataId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public EventForm getEventForm() {
        return eventForm;
    }

    public void setEventForm(EventForm eventForm) {
        this.eventForm = eventForm;
    }
}
