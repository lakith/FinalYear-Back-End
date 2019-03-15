package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.dto.EventDisplayDTO;
import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.model.EventType;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.model.User;
import com.finalproj.finalproject.repository.EventRepository;
import com.finalproj.finalproject.repository.EventTypeRepository;
import com.finalproj.finalproject.repository.UserRepository;
import com.finalproj.finalproject.service.EventUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class EventUserServiceImpl implements EventUserService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventTypeRepository eventTypeRepository;

    @Autowired
    private AmazonClient amazonClient;

    @Override
    public ResponseEntity<?> getEventsByEventType(int eventTypeId, Principal principal) throws ParseException {

        Optional<EventType> eventTypeOptional = eventTypeRepository.findById(eventTypeId);
        if(!eventTypeOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(principal.getName()));

        if(!optionalUser.isPresent()) {
            return new ResponseEntity<>(new ResponseModel("Invalid User","Invalid User",false),HttpStatus.BAD_REQUEST);
        }

        List<Event> eventList = optionalUser.get().getEventList();

        if(eventList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EventDisplayDTO> filterdList = new ArrayList<>();

        for(Event event : eventList){
            if(event.getEventType().getEventTypeId() == eventTypeId){
                EventDisplayDTO eventDisplayDTO = getDisplayFormat(event);
                filterdList.add(eventDisplayDTO);
            }
        }

        return new ResponseEntity<>(filterdList,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getFreeOrPrivateEvents(boolean paidEvent , boolean freeEvent, Principal principal) throws ParseException {


        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(principal.getName()));

        if(!optionalUser.isPresent()) {
            return new ResponseEntity<>(new ResponseModel("Invalid User","Invalid User.",false),HttpStatus.BAD_REQUEST);
        }

        List<Event> eventList = optionalUser.get().getEventList();

        if(eventList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EventDisplayDTO> filterdList = new ArrayList<>();

        for(Event event : eventList){
            if(paidEvent){
                if(event.isPaidEvent()){
                    EventDisplayDTO eventDisplayDTO = getDisplayFormat(event);
                    filterdList.add(eventDisplayDTO);
                }
            } else if(freeEvent){
                if(event.isFreeEvent()){
                    EventDisplayDTO eventDisplayDTO = getDisplayFormat(event);
                    filterdList.add(eventDisplayDTO);
                }
            }
        }

        return new ResponseEntity<>(filterdList,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getNewOrOldEvents(boolean oldEvents, boolean newEvents ,Principal principal) throws ParseException {

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(principal.getName()));

        if(!optionalUser.isPresent()) {
            return new ResponseEntity<>(new ResponseModel("Invalid User!","Invalid User!",false),HttpStatus.BAD_REQUEST);
        }

        List<Event> eventList = optionalUser.get().getEventList();

        if(eventList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EventDisplayDTO> filterdList = new ArrayList<>();

        for(Event event : eventList){

            Date eventDate = null;
            Date today = null;
            try {
                eventDate = event.getEventEndDate();
                today = Calendar.getInstance().getTime();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                eventDate = sdf.parse(sdf.format(eventDate));
                today = sdf.parse(sdf.format(today));
            } catch (ParseException e) {
                throw e;
            }

            if(oldEvents) {
                if (eventDate.compareTo(today) < 0) {
                    EventDisplayDTO eventDisplayDTO = getDisplayFormat(event);
                    filterdList.add(eventDisplayDTO);
                }
            } else if (newEvents){
                if (eventDate.compareTo(today) > 0) {
                    EventDisplayDTO eventDisplayDTO = getDisplayFormat(event);
                    filterdList.add(eventDisplayDTO);
                }
            }
        }

        return new ResponseEntity<>(filterdList,HttpStatus.OK);
    }


    private EventDisplayDTO getDisplayFormat (Event event) throws ParseException {

        String eventUrl = amazonClient.getUrlFromFileName(event.getEventThumbnail());
        EventDisplayDTO eventDisplayDTO = new EventDisplayDTO();
        eventDisplayDTO.setEventId(event.getEventId());
        eventDisplayDTO.setEventCreators(event.getEventCreators());
        eventDisplayDTO.setEventType(event.getEventType());
        eventDisplayDTO.setEventName(event.getEventName());
        eventDisplayDTO.setEventThumbnail(eventUrl);
        eventDisplayDTO.setEventStartDate(event.getEventStartDate());
        eventDisplayDTO.setEventEndDate(event.getEventEndDate());
        eventDisplayDTO.setEventPlace(event.getEventPlace());
        eventDisplayDTO.setEventHostedUrl(event.getEventHostedUrl());
        eventDisplayDTO.setNumberOfGuests(event.getNumberOfGuests());
        eventDisplayDTO.setPaidEvent(event.isPaidEvent());
        eventDisplayDTO.setPaidEventData(event.getPaidEventData());
        eventDisplayDTO.setFreeEvent(event.isFreeEvent());
        eventDisplayDTO.setEventPrivate(event.isEventPrivate());
        eventDisplayDTO.setEventPublic(event.isEventPublic());
        eventDisplayDTO.setEventSpecialGuests(event.getEventSpecialGuests());
        eventDisplayDTO.setSpecialGuestEmails(event.getSpecialGuestEmails());
        eventDisplayDTO.setGenaralGuestMails(event.getGenaralGuestMails());
        eventDisplayDTO.setEventFrontPage(event.getEventFrontPage());
        eventDisplayDTO.setEventComments(event.getEventComments());

        Date eventDate = null;
        Date today = null;
        try {
            eventDate = event.getEventEndDate();
            today = Calendar.getInstance().getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            eventDate = sdf.parse(sdf.format(eventDate));
            today = sdf.parse(sdf.format(today));
        } catch (ParseException e) {
            throw e;
        }

        if (eventDate.compareTo(today) < 0) {
            eventDisplayDTO.setClosed(true);
        }

        return eventDisplayDTO;
    }
}
