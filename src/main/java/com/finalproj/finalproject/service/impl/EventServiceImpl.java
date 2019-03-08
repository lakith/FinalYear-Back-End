package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.dto.*;
import com.finalproj.finalproject.model.*;
import com.finalproj.finalproject.repository.*;
import com.finalproj.finalproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventTypeRepository eventTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventCommentsRepositroy eventCommentsRepositroy;

    @Autowired
    AmazonClient amazonClient;

    @Autowired
    EventFrontPageRepository eventFrontPageRepository;

    @Autowired
    PaidEventRepository paidEventRepository;

    @Override
    public ResponseEntity<?> saveEventBaseDetails(EventBaseDetailsDTO eventBaseDetailsDTO, Principal principal) throws Exception {

        Event event = new Event();

        Optional<EventType> eventTypeOptional = eventTypeRepository.findById(eventBaseDetailsDTO.getEventTypeId());
        if(!eventTypeOptional.isPresent()){
            return new ResponseEntity<>(new ResponseModel("invalied Event Type id","invalied Event Type id", false),HttpStatus.BAD_REQUEST);
        }

        int userId = Integer.parseInt(principal.getName());

        Optional<User> optionalUser = userRepository.findById(userId);

        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("User is not active or invalied User","User is not active or invalied User", false),HttpStatus.BAD_REQUEST);
        }

        List<User> userList = new ArrayList<>();
        userList.add(optionalUser.get());

        event.setEventName(eventBaseDetailsDTO.getEventHeading());
        event.setEventStartDate(eventBaseDetailsDTO.getEventStartDate());
        event.setEventEndDate(eventBaseDetailsDTO.getEventEndDate());
        event.setEventPlace(eventBaseDetailsDTO.getEventPlace());
        event.setEventHostedUrl(eventBaseDetailsDTO.getEventHostedUrl());
        event.setEventType(eventTypeOptional.get());
        event.setEventCreators(userList);

        String eventThumbnail = amazonClient.uploadFile(eventBaseDetailsDTO.getFile(),true);
        event.setEventThumbnail(eventThumbnail);

        try {
            event = eventRepository.save(event);

            User user = optionalUser.get();
            List<Event> eventList = user.getEventList();

            if(eventList.isEmpty()){
                eventList = new ArrayList<>();
                eventList.add(event);
            } else {
                eventList.add(event);
            }

            user.setEventList(eventList);

            userRepository.save(user);

            return new ResponseEntity<>(event, HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }


    }

    public ResponseEntity<?> saveOtherEventDetails(EventOtherDetailsDTO eventOtherDetailsDTO) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(eventOtherDetailsDTO.getEventId());
        if(!optionalEvent.isPresent()){
           return new ResponseEntity<>(new ResponseModel("Invalied Event Id","Invalied Event Id",false),HttpStatus.BAD_REQUEST);
        }

        Event event = optionalEvent.get();

        int eventGroupId = eventOtherDetailsDTO.getEventGroupId();
        if(eventGroupId == 1){
            event.setEventPublic(true);
            event.setEventPrivate(false);
        }   else if(eventGroupId == 2) {
            event.setEventPublic(false);
            event.setEventPrivate(true);
        }
        int eventCategoryId = eventOtherDetailsDTO.getEventCategoryId();
        if(eventCategoryId == 1) {
            event.setPaidEvent(true);
            event.setFreeEvent(false);
        } else if(eventCategoryId == 2){
            event.setPaidEvent(false);
            event.setFreeEvent(true);
        }

        event.setNumberOfGuests(eventOtherDetailsDTO.getMaximumNumberOfGuests());

        try {
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateEventThumbnail(EventThumbnailDTO eventThumbnailDTO) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(eventThumbnailDTO.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid Event Id","Invalied Event Id",false),HttpStatus.BAD_REQUEST);
        }

        Event event = optionalEvent.get();

        String eventThumbnail = amazonClient.uploadFile(eventThumbnailDTO.getFile(),true);

        event.setEventThumbnail(eventThumbnail);

        try {
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
