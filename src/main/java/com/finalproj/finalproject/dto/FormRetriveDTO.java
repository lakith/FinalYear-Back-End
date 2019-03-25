package com.finalproj.finalproject.dto;

import java.util.List;

public class FormRetriveDTO {

    private int eventId;

    private List<FormConfigDTO> formConfigDTOS;

    public FormRetriveDTO() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public List<FormConfigDTO> getFormConfigDTOS() {
        return formConfigDTOS;
    }

    public void setFormConfigDTOS(List<FormConfigDTO> formConfigDTOS) {
        this.formConfigDTOS = formConfigDTOS;
    }
}
