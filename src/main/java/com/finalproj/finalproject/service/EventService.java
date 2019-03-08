package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.*;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface EventService {

    ResponseEntity<?> saveEventBaseDetails(EventBaseDetailsDTO eventBaseDetailsDTO, Principal principal) throws Exception ;

    ResponseEntity<?> saveOtherEventDetails(EventOtherDetailsDTO eventOtherDetailsDTO) throws Exception;

    ResponseEntity<?> updateEventThumbnail(EventThumbnailDTO eventThumbnailDTO) throws Exception;

}
