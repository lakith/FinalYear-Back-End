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

    public ResponseEntity<?> addNewAdminUsers(EventAdminUsers eventAdminUsers) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(eventAdminUsers.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id.","Invalied Event Id.",false),HttpStatus.BAD_REQUEST);
        }
        Event event = optionalEvent.get();
        List<User> eventCreators = event.getEventCreators();

        if(eventCreators.isEmpty()){
            return new ResponseEntity<>(new ResponseModel("There must be at least one event creator","There must be at least one event creator",false),HttpStatus.BAD_REQUEST);
        }

        for(int i : eventAdminUsers.getAdminUsers()){
            Optional<User> optionalUser = userRepository.findById(i);
            if(!optionalUser.isPresent()){
                return new ResponseEntity<>(new ResponseModel("Invalid user ID present","Invalid user ID present",false),HttpStatus.BAD_REQUEST);
            }
            eventCreators.add(optionalUser.get());
        }

        event.setEventCreators(eventCreators);

        try {
            eventRepository.save(event);
            return new ResponseEntity<>(new ResponseModel("Users added successfully","Users added successfully",true),HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public ResponseEntity<?> saveEventFrontPageDetails(EventFrontPageDTO eventFrontPageDTO,Principal principal) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(eventFrontPageDTO.getEventID());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id.","Invalied Event Id.",false),HttpStatus.BAD_REQUEST);
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
            eventRepository.save(event);
            return new ResponseEntity<>(new ResponseModel("Saved Successfully.","Saved Successfully.",true),HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    public ResponseEntity<?> saveComment (CommentDTO commentDTO,Principal principal) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(commentDTO.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id.","Invalied Event Id",false),HttpStatus.BAD_REQUEST);
        }
        Event event = optionalEvent.get();

        List<EventComments> eventCommentsList = event.getEventComments();

        EventComments eventComments = new EventComments();
        eventComments.setComment(commentDTO.getComment());

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(principal.getName()));
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied User Id","Invalied User Id",false),HttpStatus.BAD_REQUEST);
        }
        eventComments.setCommenter(optionalUser.get());
        eventComments = eventCommentsRepositroy.save(eventComments);

        if(eventCommentsList.isEmpty()){
            eventCommentsList = new ArrayList<>();
            eventCommentsList.add(eventComments);
        } else if(!eventCommentsList.isEmpty()){
            eventCommentsList.add(eventComments);
        }
        event.setEventComments(eventCommentsList);
        try {
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private ResponseEntity<?> savePeymentDetails  (PaidEventDetailsDto paidEventDetailsDto) throws Exception {

        Optional<Event> optionalEvent = eventRepository.findById(paidEventDetailsDto.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid Event Id.","Invalied Event Id",false),HttpStatus.BAD_REQUEST);
        }
        Event event = optionalEvent.get();

        if(!event.isPaidEvent()){
            return new ResponseEntity<>(new ResponseModel("You cannot specify payment details for Free event.","You cannot specify payment details for Free event.",false),HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        }

        PaidEvent paidEvent = new PaidEvent();

        if(paidEvent.isSellOnlineByUser()){
            if(!paidEventDetailsDto.getSellingWebUrl().trim().isEmpty()){
                paidEvent.setSellingWebUrl(paidEventDetailsDto.getSellingWebUrl());
                paidEvent = paidEventRepository.save(paidEvent);
            } else {
                return new ResponseEntity<>(new ResponseModel("You Must specify payment web url.","You Must specify payment web url.",false),HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
            }
        } else if(paidEvent.isRsvpSelling()){
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
            event.setEventPrivate(true);
            event.setEventPublic(false);
        } else if(eventCategoryId == 2){
            event.setEventPrivate(false);
            event.setEventPublic(true);
        }

        event.setNumberOfGuests(eventOtherDetailsDTO.getMaximumNumberOfGuests());

        try {
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
