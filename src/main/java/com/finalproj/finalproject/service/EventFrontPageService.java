package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.EventFrontPageDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface EventFrontPageService {

    ResponseEntity<?> saveEventFrontPageDetails(EventFrontPageDTO eventFrontPageDTO, Principal principal) throws Exception;
}
