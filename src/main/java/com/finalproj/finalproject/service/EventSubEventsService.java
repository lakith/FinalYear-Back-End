package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.EventSubEventDTO;
import org.springframework.http.ResponseEntity;

public interface EventSubEventsService {

    ResponseEntity<?> viewSubEvents(int eventId);
    ResponseEntity<?> saveSubEvent(EventSubEventDTO subEventDTO, int eventId) throws Exception;

}
