package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.PaidEventDetailsDto;
import com.finalproj.finalproject.service.EventPeymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/event-payment")
public class EventPeymentController {

    @Autowired
    EventPeymentService eventPeymentService;

    @PostMapping
    public ResponseEntity saveEventPeymentDetails(@RequestBody @Valid  PaidEventDetailsDto paidEventDetailsDto) throws Exception {
        return eventPeymentService.savePeymentDetails(paidEventDetailsDto);
    }



}
