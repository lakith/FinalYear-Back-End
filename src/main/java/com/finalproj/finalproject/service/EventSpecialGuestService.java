package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.GuestMailsDTO;
import com.finalproj.finalproject.dto.UserConfirmationDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface EventSpecialGuestService {

    ResponseEntity<?> sendmailsForSpecialGuest(GuestMailsDTO guestMailsDTO) throws Exception;

    ResponseEntity<?> confirmUserAttendance(UserConfirmationDTO userConfirmationDTO, Principal principal) throws Exception;
}
