package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.service.EventUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;

@RestController
@RequestMapping("/event-user")
@CrossOrigin
public class EventUserController {

    @Autowired
    EventUserService eventUserService;

    @GetMapping("/filter-by-date")
    public ResponseEntity<?> getNewOrOldEvents(@RequestParam("old-event") boolean oldEvents,@RequestParam("new-event") boolean newEvents , Principal principal) throws ParseException{
        return eventUserService.getNewOrOldEvents(oldEvents,newEvents,principal);
    }

    @GetMapping("/filter-by-category")
    public ResponseEntity<?> getFreeOrPrivateEvents(@RequestParam("paid-event")boolean paidEvent ,@RequestParam("free-event") boolean freeEvent, Principal principal) throws ParseException {
        return eventUserService.getFreeOrPrivateEvents(paidEvent,freeEvent,principal);
    }

    @GetMapping("/filter-by-event-type")
    public ResponseEntity<?> getEventsByEventType(@RequestParam("event-type") int eventTypeId, Principal principal) throws ParseException {
        return eventUserService.getEventsByEventType(eventTypeId,principal);
    }
}
