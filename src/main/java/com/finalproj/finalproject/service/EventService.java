package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.*;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface EventService {

    ResponseEntity<?> saveEventBaseDetails(EventBaseDetailsDTO eventBaseDetailsDTO, Principal principal) throws Exception ;

    ResponseEntity<?> saveOtherEventDetails(EventOtherDetailsDTO eventOtherDetailsDTO) throws Exception;

    ResponseEntity<?> updateEventThumbnail(EventThumbnailDTO eventThumbnailDTO) throws Exception;

    ResponseEntity<?> getOneEventDetails(int eventId,Principal principal);

    ResponseEntity<?> getALlEvents();

    ResponseEntity<?> getALLPrivateOrPublicEvents(boolean privateEvent , boolean publicEvent);

    ResponseEntity<?> getALLFreeOrPaid(boolean paid , boolean free);

    ResponseEntity<?> getALLEventsByEventType(int eventTypeId);
}
