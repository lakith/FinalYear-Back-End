package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.EventSubEventDTO;
import com.finalproj.finalproject.service.EventSubEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sub-event")
public class EventSubEventController {

    @Autowired
    EventSubEventsService eventSubEventsService;

    @PostMapping("/sub-event-save")
    public ResponseEntity<?> eventSubEventSave(@RequestParam("event-id")int eventId, @RequestBody EventSubEventDTO subEventDTO) throws Exception {
        return eventSubEventsService.saveSubEvent(subEventDTO, eventId);
    }

    @GetMapping
    public ResponseEntity<?> viewSubEvents(@RequestParam("event-id")int eventid){
        return eventSubEventsService.viewSubEvents(eventid);
    }

}
