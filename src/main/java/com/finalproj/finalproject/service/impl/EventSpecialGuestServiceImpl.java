package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.Enums.MealPreferance;
import com.finalproj.finalproject.Enums.UserConfirmation;
import com.finalproj.finalproject.data.MailBody;
import com.finalproj.finalproject.dto.GuestMailsDTO;
import com.finalproj.finalproject.dto.UserConfirmationDTO;
import com.finalproj.finalproject.model.*;
import com.finalproj.finalproject.repository.*;
import com.finalproj.finalproject.service.EventSpecialGuestService;
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
public class EventSpecialGuestServiceImpl implements EventSpecialGuestService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpecialGuestMailRepository specialGuestMailRepository;

    @Autowired
    EventSpecialGuestsRepository eventSpecialGuestsRepository;

    @Autowired
    SpecialGuestRepository specialGuestRepository;

    public ResponseEntity<?> sendmailsForSpecialGuest(GuestMailsDTO guestMailsDTO) throws Exception {

        Optional<Event> optionalEvent = eventRepository.findById(guestMailsDTO.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id","Invalied Event Id.",false), HttpStatus.BAD_REQUEST);
        }

        Event event = optionalEvent.get();
        MailBody mailBody = new MailBody();
        String mailBodyMessage = mailBody.getSpecialGuestMailAddress(event.getEventName());
        List<SpecialGuestEmails> specialGuestEmails;

        if(event.getSpecialGuestEmails().isEmpty()){
            specialGuestEmails = new ArrayList<>();
        } else {
            specialGuestEmails = event.getSpecialGuestEmails();
        }

        for(String mail : guestMailsDTO.getEmails()){
            SendGridMailClient.sendMail(mailBodyMessage,mail,event.getEventName());
            SpecialGuestEmails guestEmail = new SpecialGuestEmails();
            guestEmail.setMail(mail);
            guestEmail = specialGuestMailRepository.save(guestEmail);
            specialGuestEmails.add(guestEmail);
        }
        event.setSpecialGuestEmails(specialGuestEmails);
        try {
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<?> confirmUserAttendance(UserConfirmationDTO userConfirmationDTO, Principal principal) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(userConfirmationDTO.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid Event Id. ","Invalid Event Id.",false), HttpStatus.BAD_REQUEST);
        }

        Event event = optionalEvent.get();

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(principal.getName()));
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid User Id.","Invalid User Id",false),HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

        EventSpecialGuests eventSpecialGuests = event.getEventSpecialGuests();
        List<SpecialGuest> specialGuestsList;
        if(eventSpecialGuests != null){
            specialGuestsList =  event.getEventSpecialGuests().getSpecialGuest();
            if(specialGuestsList.isEmpty()){
                specialGuestsList = new ArrayList<>();
            }else {
                for (SpecialGuest guest : specialGuestsList){
                    if(guest.getSpecialUser().getUserId() == user.getUserId()){
                        return new ResponseEntity<>(new ResponseModel("User Already Confirmed","User Already Confirmed",false),HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } else{
            eventSpecialGuests = new EventSpecialGuests();
            specialGuestsList = new ArrayList<>();
        }

        SpecialGuest specialGuest = new SpecialGuest();
        specialGuest.setSpecialUser(user);

        if(userConfirmationDTO.getConfirmation() == 1) {
            specialGuest.setConfirmation(UserConfirmation.COMMING);
        } else if(userConfirmationDTO.getConfirmation() == 2){
            specialGuest.setConfirmation(UserConfirmation.NOT_SURE);
        } else {
            specialGuest.setConfirmation(UserConfirmation.NOT_COMMING);
        }

        if(userConfirmationDTO.getMealPreference() == 1){
            specialGuest.setMealPreferance(MealPreferance.VEG);
        } else {
            specialGuest.setMealPreferance(MealPreferance.NON_VEG);
        }

        try {
            specialGuest = specialGuestRepository.save(specialGuest);
            specialGuestsList.add(specialGuest);
            eventSpecialGuests.setSpecialGuest(specialGuestsList);
            eventSpecialGuests = eventSpecialGuestsRepository.save(eventSpecialGuests);
            event.setEventSpecialGuests(eventSpecialGuests);
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<?> getEventSpecialGuestList(int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id. ","Invalid Event Id.",false), HttpStatus.BAD_REQUEST);
        }
        List<SpecialGuest>  specialGuestList = new ArrayList<>();
        Event event = optionalEvent.get();

        if(event.getEventSpecialGuests() != null){
            specialGuestList = event.getEventSpecialGuests().getSpecialGuest();
        } else {
            return new ResponseEntity<>(new ResponseModel("No events display","No events display",false),HttpStatus.BAD_REQUEST);
        }

        if(!specialGuestList.isEmpty()){
            return new ResponseEntity<>(specialGuestList,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseModel("No events display","No events display",false),HttpStatus.BAD_REQUEST);
        }

    }
}
