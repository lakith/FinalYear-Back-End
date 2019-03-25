package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.FormRetriveDTO;
import org.springframework.http.ResponseEntity;

public interface EventFormService {

    ResponseEntity<?> saveEventFormData(int eventId, String eventData);
    ResponseEntity<?> saveEventConfigData(FormRetriveDTO formRetriveDTO) throws Exception;
    ResponseEntity<?> getEventForm(int eventId);
}
