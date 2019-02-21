package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.model.EventType;
import com.finalproj.finalproject.service.EventTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/event-type")
@CrossOrigin
public class EventTypesController {

    @Autowired
    EventTypesService eventTypesService;

    @PostMapping("/save")
    public ResponseEntity saveEventType(@RequestBody @Valid  EventType eventType) throws Exception {
       return eventTypesService.saveEventType(eventType);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEventType(@RequestBody @Valid EventType eventType,@PathVariable("id") int id) throws Exception {
        return eventTypesService.updateEventType(eventType,id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEventType(@PathVariable("id") int id) throws Exception {
        return eventTypesService.deleteEventType(id);
    }
}
