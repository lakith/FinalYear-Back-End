package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.EventBaseDetailsDTO;
import com.finalproj.finalproject.dto.EventOtherDetailsDTO;
import com.finalproj.finalproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity saveEventBaseDetails(
            @RequestParam MultipartFile thumbnail,
            @RequestParam(required = true)  String eventHeading,
            @RequestParam(required = true)  Date eventStartDate,
            @RequestParam(required = true)  Date eventEndDate,
            @RequestParam(required = true)  String eventPlace,
            @RequestParam(required = true)  String eventHostedUrl,
            @RequestParam(required = false)  int eventTypeId,
            Principal principal
    ) throws Exception {

        EventBaseDetailsDTO eventBaseDetailsDTO = new EventBaseDetailsDTO();
        eventBaseDetailsDTO.setEventHeading(eventHeading);
        eventBaseDetailsDTO.setEventStartDate(eventStartDate);
        eventBaseDetailsDTO.setEventEndDate(eventEndDate);
        eventBaseDetailsDTO.setEventPlace(eventPlace);
        eventBaseDetailsDTO.setEventHostedUrl(eventHostedUrl);
        eventBaseDetailsDTO.setEventTypeId(eventTypeId);
        eventBaseDetailsDTO.setFile(thumbnail);

       return eventService.saveEventBaseDetails(eventBaseDetailsDTO,principal);

    }

    @GetMapping("/get-one-event-data")
    public ResponseEntity getOneEvent(@RequestParam("event-id") int eventId,Principal principal){
        return eventService.getOneEventDetails(eventId,principal);
    }

    @PostMapping("/other-data")
    public ResponseEntity saveOtherData(@RequestBody @Valid EventOtherDetailsDTO eventOtherDetailsDTO) throws Exception {
        return eventService.saveOtherEventDetails(eventOtherDetailsDTO);
    }
}


