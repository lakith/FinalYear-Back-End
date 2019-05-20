package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.data.MailBody;
import com.finalproj.finalproject.dto.EventSubEventDTO;
import com.finalproj.finalproject.dto.SubEventsGuestsDTO;
import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.model.EventSubEvents;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.model.SubEventGuests;
import com.finalproj.finalproject.repository.*;
import com.finalproj.finalproject.service.EventSubEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventSubEventsServiceImpl implements EventSubEventsService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventSubEventsRepository eventSubEventsRepository;

    @Autowired
    SubEventsGuestsRepository subEventsGuestsRepository;

    public ResponseEntity<?> saveSubEvent(EventSubEventDTO subEventDTO,int eventId) throws Exception {

        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event id ","Invalied Event id ", false), HttpStatus.BAD_REQUEST);
        }

        EventSubEvents eventSubEvents = new EventSubEvents();
        eventSubEvents.setEvent(optionalEvent.get());
        eventSubEvents.setSubEventName(subEventDTO.getSubEventName());
        eventSubEvents.setEventDate(subEventDTO.getEventDate());
        eventSubEvents.setStartTime(subEventDTO.getTime());

        eventSubEvents = eventSubEventsRepository.save(eventSubEvents);

        List<SubEventGuests> subEventGuestsList = new ArrayList<>();
        for(SubEventsGuestsDTO subEventsGuestsDTO : subEventDTO.getSubEventsGuestsDTOS()){
            SubEventGuests subEventGuest = new SubEventGuests();
            subEventGuest.setEventSubEvents(eventSubEvents);
            subEventGuest.setGuestName(subEventsGuestsDTO.getName());
            subEventGuest.setGuestEmail(subEventsGuestsDTO.getEmail());
            subEventGuest = subEventsGuestsRepository.save(subEventGuest);
            subEventGuestsList.add(subEventGuest);

            MailBody mailBody = new MailBody();

            String mail = mailBody.getSubEventEamail(optionalEvent.get().getEventName(),eventSubEvents.getSubEventName(),eventSubEvents.getEventDate().toString(),eventSubEvents.getStartTime());
            SendGridMailClient.sendMail(mail,subEventsGuestsDTO.getEmail(),subEventDTO.getSubEventName());
        }

        eventSubEvents.setSubEventGuests(subEventGuestsList);

        try {
            eventSubEvents = eventSubEventsRepository.save(eventSubEvents);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        List<EventSubEvents> subEventsList = new ArrayList<>();
        if(!optionalEvent.get().getEventSubEvents().isEmpty()){
            subEventsList = optionalEvent.get().getEventSubEvents();
        }
        subEventsList.add(eventSubEvents);

        Event event = optionalEvent.get();
        event.setEventSubEvents(subEventsList);

        try {
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public ResponseEntity<?> viewSubEvents(int eventId){
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event id ","Invalied Event id ", false), HttpStatus.BAD_REQUEST);
        }

        List<EventSubEvents> subEventsList = optionalEvent.get().getEventSubEvents();
        if(subEventsList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(subEventsList,HttpStatus.OK);
        }
    }

}
