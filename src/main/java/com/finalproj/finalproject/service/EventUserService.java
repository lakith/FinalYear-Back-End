package com.finalproj.finalproject.service;

import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.text.ParseException;

public interface EventUserService  {

    ResponseEntity<?> getNewOrOldEvents(boolean oldEvents, boolean newEvents , Principal principal) throws ParseException;
    ResponseEntity<?> getFreeOrPrivateEvents(boolean paidEvent , boolean freeEvent, Principal principal) throws ParseException;
    ResponseEntity<?> getEventsByEventType(int eventTypeId, Principal principal) throws ParseException;
}
