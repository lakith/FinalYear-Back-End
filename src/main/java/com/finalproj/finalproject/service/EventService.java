package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.EventAdminUsers;
import com.finalproj.finalproject.dto.EventBaseDetailsDTO;
import com.finalproj.finalproject.dto.EventFrontPageDTO;
import com.finalproj.finalproject.dto.EventOtherDetailsDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface EventService {

    ResponseEntity<?> saveEventBaseDetails(EventBaseDetailsDTO eventBaseDetailsDTO, Principal principal) throws Exception ;

    ResponseEntity<?> saveOtherEventDetails(EventOtherDetailsDTO eventOtherDetailsDTO) throws Exception;


}
