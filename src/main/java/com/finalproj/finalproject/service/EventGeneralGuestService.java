package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.GuestMailsDTO;
import com.finalproj.finalproject.dto.UserConfirmationDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface EventGeneralGuestService {

    ResponseEntity<?> sendmailsForSpecialGuest(GuestMailsDTO guestMailsDTO) throws Exception;

    ResponseEntity<?> confirmUser(UserConfirmationDTO userConfirmationDTO, Principal principal) throws Exception;
}
