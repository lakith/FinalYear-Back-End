package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.*;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.text.ParseException;

public interface EventService {

    ResponseEntity<?> saveEventBaseDetails(EventBaseDetailsDTO eventBaseDetailsDTO, Principal principal) throws Exception ;

    ResponseEntity<?> saveOtherEventDetails(EventOtherDetailsDTO eventOtherDetailsDTO) throws Exception;

    ResponseEntity<?> updateEventThumbnail(EventThumbnailDTO eventThumbnailDTO) throws Exception;

    ResponseEntity<?> getOneEventDetails(int eventId,Principal principal);

    ResponseEntity<?> getALlEvents() throws ParseException;

    ResponseEntity<?> getALLPrivateOrPublicEvents(boolean privateEvent , boolean publicEvent) throws ParseException;

    ResponseEntity<?> getALLFreeOrPaid(boolean paid , boolean free) throws ParseException;

    ResponseEntity<?> getALLEventsByEventType(int eventTypeId) throws ParseException;

    ResponseEntity<?> getOneEventForDisplay(int eventId) throws ParseException;
}
