package com.finalproj.finalproject.service;

import com.finalproj.finalproject.model.EventType;
import org.springframework.http.ResponseEntity;

public interface EventTypesService {

    ResponseEntity<?> saveEventType(EventType eventType) throws Exception;

    ResponseEntity<?> updateEventType(EventType eventType, int eventTypeId) throws Exception;

    ResponseEntity<?> deleteEventType(int eventTypeId) throws Exception;

}
