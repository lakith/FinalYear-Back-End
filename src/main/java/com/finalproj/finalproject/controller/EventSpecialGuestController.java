package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.GuestMailsDTO;
import com.finalproj.finalproject.dto.UserConfirmationDTO;
import com.finalproj.finalproject.service.EventSpecialGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/special-guest")
@CrossOrigin
public class EventSpecialGuestController {

    @Autowired
    EventSpecialGuestService eventSpecialGuestService;

    @GetMapping("/test")
    public ResponseEntity test(){
        GuestMailsDTO guestMailsDTO =  new GuestMailsDTO();
        guestMailsDTO.setEventId(1);
        List<String> stringList = new ArrayList<>();
        stringList.add("lakith1995@gmail.com");
        stringList.add("sachincloud@gmail.com");
        guestMailsDTO.setEmails(stringList);
        return new ResponseEntity(guestMailsDTO, HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity sendEmailsToSpecialGuest(@RequestBody @Valid GuestMailsDTO guestMailsDTO) throws Exception {
       return eventSpecialGuestService.sendmailsForSpecialGuest(guestMailsDTO);
    }

    @PostMapping("/confirm-user")
    public ResponseEntity confirmUserAttendance(@RequestBody @Valid UserConfirmationDTO userConfirmationDTO, Principal principal) throws Exception{
        return eventSpecialGuestService.confirmUserAttendance(userConfirmationDTO, principal);
    }

}
