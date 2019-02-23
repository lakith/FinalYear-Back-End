package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.EventBaseDetailsDTO;
import com.finalproj.finalproject.dto.EventOtherDetailsDTO;
import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventMainController {

    @Autowired
    EventService eventService;

    @GetMapping("test")
    public ResponseEntity test(){
        EventBaseDetailsDTO eventBaseDetailsDTO = new EventBaseDetailsDTO();
        eventBaseDetailsDTO.setEventStartDate(java.util.Calendar.getInstance().getTime());
        eventBaseDetailsDTO.setEventEndDate(java.util.Calendar.getInstance().getTime());
        eventBaseDetailsDTO.setEventPlace("Panadura");
        eventBaseDetailsDTO.setEventHostedUrl("www.gooogle.com");
        eventBaseDetailsDTO.setEventTypeId(19);
        eventBaseDetailsDTO.setEventHeading("My Birthday");

        return new ResponseEntity(eventBaseDetailsDTO, HttpStatus.OK);

    }

    @PostMapping("/eventBaseSave")
    public ResponseEntity saveEventBaseDetails(@RequestBody @Valid EventBaseDetailsDTO eventBaseDetailsDTO, Principal principal) throws Exception {
       return eventService.saveEventBaseDetails(eventBaseDetailsDTO,principal);
    }

    @PostMapping("/other-data")
    public ResponseEntity saveOtherData(@RequestBody @Valid EventOtherDetailsDTO eventOtherDetailsDTO) throws Exception {
        return eventService.saveOtherEventDetails(eventOtherDetailsDTO);
    }


}
