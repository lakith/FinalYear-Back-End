package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.dto.EventFrontPageDTO;
import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.model.EventFrontPage;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.repository.*;
import com.finalproj.finalproject.service.EventFrontPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@Service
@Transactional
public class EventFrontPageServiceImpl implements EventFrontPageService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AmazonClient amazonClient;

    @Autowired
    EventFrontPageRepository eventFrontPageRepository;

    @Override
    public ResponseEntity<?> saveEventFrontPageDetails(EventFrontPageDTO eventFrontPageDTO, Principal principal) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(eventFrontPageDTO.getEventID());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid Event Id.","Invalid Event Id.",false), HttpStatus.BAD_REQUEST);
        }
        Event event = optionalEvent.get();

        EventFrontPage eventFrontPage = new EventFrontPage();
        eventFrontPage.setContent(eventFrontPageDTO.getContent());
        eventFrontPage.setOtherDetails(eventFrontPageDTO.getOtherDetails());
        eventFrontPage.setTermsAndConditions(eventFrontPageDTO.getTermsAndConditions());

        String topImage = amazonClient.uploadFile(eventFrontPageDTO.getFrontImage(),true);

        eventFrontPage.setTopImage(topImage);

        try {
            eventFrontPage = eventFrontPageRepository.save(eventFrontPage);
            event.setEventFrontPage(eventFrontPage);
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
