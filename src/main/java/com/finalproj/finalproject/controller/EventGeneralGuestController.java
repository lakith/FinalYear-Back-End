package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.GuestMailsDTO;
import com.finalproj.finalproject.dto.UserConfirmationDTO;
import com.finalproj.finalproject.service.EventGeneralGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/general-guest")
@CrossOrigin
public class EventGeneralGuestController {

    @Autowired
    EventGeneralGuestService eventGeneralGuestService;

    @PostMapping("/email")
    public ResponseEntity sendMails(@RequestBody @Valid GuestMailsDTO guestMailsDTO) throws Exception{
        return eventGeneralGuestService.sendmailsForSpecialGuest(guestMailsDTO);
    }

    @PostMapping("/confirm-user")
    public ResponseEntity confirmUser(@RequestBody @Valid UserConfirmationDTO userConfirmationDTO, Principal principal) throws Exception {
        return eventGeneralGuestService.confirmUser(userConfirmationDTO,principal);
    }

}
