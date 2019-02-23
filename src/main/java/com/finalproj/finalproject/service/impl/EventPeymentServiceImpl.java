package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.dto.PaidEventDetailsDto;
import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.model.PaidEvent;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.repository.*;
import com.finalproj.finalproject.service.EventPeymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EventPeymentServiceImpl implements EventPeymentService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaidEventRepository paidEventRepository;


    @Override
    public ResponseEntity<?> savePeymentDetails  (PaidEventDetailsDto paidEventDetailsDto) throws Exception {

        Optional<Event> optionalEvent = eventRepository.findById(paidEventDetailsDto.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid Event Id.","Invalied Event Id",false), HttpStatus.BAD_REQUEST);
        }
        Event event = optionalEvent.get();

        if(!event.isPaidEvent()){
            return new ResponseEntity<>(new ResponseModel("You cannot specify payment details for Free event.","You cannot specify payment details for Free event.",false),HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        }

        PaidEvent paidEvent = new PaidEvent();

        if(paidEventDetailsDto.isSellOnlineByUser()){
            if(!paidEventDetailsDto.getSellingWebUrl().trim().isEmpty()){
                paidEvent.setSellingWebUrl(paidEventDetailsDto.getSellingWebUrl());
                paidEvent = paidEventRepository.save(paidEvent);
            } else {
                return new ResponseEntity<>(new ResponseModel("You Must specify payment web url.","You Must specify payment web url.",false),HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
            }
        } else if(paidEventDetailsDto.isRsvpSelling()){
            paidEvent.setAccountOwnerName(paidEventDetailsDto.getAccountOwnerName());
            paidEvent.setBankAccountNumber(paidEventDetailsDto.getBankAccountNumber());
            paidEvent.setBankName(paidEventDetailsDto.getBankName());
            paidEvent.setBranchName(paidEventDetailsDto.getBranchName());
            paidEvent.setAddress(paidEventDetailsDto.getAddress());
            paidEvent.setPrice(paidEventDetailsDto.getPrice());
            paidEvent = paidEventRepository.save(paidEvent);
        }

        event.setPaidEventData(paidEvent);
        try {
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
