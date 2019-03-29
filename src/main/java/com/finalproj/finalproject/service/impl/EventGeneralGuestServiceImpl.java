package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.Enums.MealPreferance;
import com.finalproj.finalproject.Enums.UserConfirmation;
import com.finalproj.finalproject.data.MailBody;
import com.finalproj.finalproject.dto.EventGenaralConfirmationDto;
import com.finalproj.finalproject.dto.GuestMailsDTO;
import com.finalproj.finalproject.dto.UserConfirmationDTO;
import com.finalproj.finalproject.model.*;
import com.finalproj.finalproject.repository.*;
import com.finalproj.finalproject.service.EventGeneralGuestService;
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
public class EventGeneralGuestServiceImpl implements EventGeneralGuestService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GeneralGuestMailRepository generalGuestMailRepository;

    @Autowired
    GeneralGuestRepository generalGuestRepository;

    @Autowired
    EventGenaralGuestsRepository eventGenaralGuestsRepository;


    public ResponseEntity<?> sendmailsForSpecialGuest(GuestMailsDTO guestMailsDTO) throws Exception {

        Optional<Event> optionalEvent = eventRepository.findById(guestMailsDTO.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id ","Invalid Event Id.",false), HttpStatus.BAD_REQUEST);
        }

        Event event = optionalEvent.get();

        MailBody mailBody = new MailBody();
        String mailBodyMessage = mailBody.getGeneralGuestMailAddress(event.getEventName());

        List<GenaralGuestMails> genaralGuestMails;

        if(event.getGenaralGuestMails().isEmpty()){
            genaralGuestMails = new ArrayList<>();
        } else {
            genaralGuestMails = event.getGenaralGuestMails();
        }

        for(String mail : guestMailsDTO.getEmails()){
            SendGridMailClient.sendMail(mailBodyMessage,mail,event.getEventName());
            GenaralGuestMails guestEmail = new GenaralGuestMails();
            guestEmail.setMail(mail);
            guestEmail = generalGuestMailRepository.save(guestEmail);
            genaralGuestMails.add(guestEmail);
        }

        event.setGenaralGuestMails(genaralGuestMails);
        try {
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<?> confirmUser(UserConfirmationDTO userConfirmationDTO, Principal principal) throws Exception {

        Optional<Event> optionalEvent = eventRepository.findById(userConfirmationDTO.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id. ","Invalid Event Id.",false), HttpStatus.BAD_REQUEST);
        }

        Event event = optionalEvent.get();

        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(principal.getName()));
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid User Id","Invalid User Id",false),HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

        EventGenaralGuests eventGenaralGuests = event.getEventGenaralGuests();
        List<GeneralGuest> eventGearaltGuestsList;
        if(eventGenaralGuests != null){
            eventGearaltGuestsList = event.getEventGenaralGuests().getGeneralGuest();
            if(eventGearaltGuestsList.isEmpty()){
                eventGearaltGuestsList = new ArrayList<>();
            }else {
                for (GeneralGuest guest : eventGearaltGuestsList){
                    if(guest.getGenaralUser().getUserId() == user.getUserId()){
                        return new ResponseEntity<>(new ResponseModel("User Already Confirmed","User Already Confirmed",false),HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } else{
            eventGenaralGuests = new EventGenaralGuests();
            eventGearaltGuestsList = new ArrayList<>();
        }


        GeneralGuest generalGuest = new GeneralGuest();
        generalGuest.setGenaralUser(user);

        if(userConfirmationDTO.getConfirmation() == 1) {
            generalGuest.setConfirmation(UserConfirmation.COMMING);
        } else if(userConfirmationDTO.getConfirmation() == 2){
            generalGuest.setConfirmation(UserConfirmation.NOT_SURE);
        } else {
            generalGuest.setConfirmation(UserConfirmation.NOT_COMMING);
        }

        if(userConfirmationDTO.getMealPreference() == 1){
            generalGuest.setMealPreferance(MealPreferance.VEG);
        } else {
            generalGuest.setMealPreferance(MealPreferance.NON_VEG);
        }

        try {
            generalGuest = generalGuestRepository.save(generalGuest);
            eventGearaltGuestsList.add(generalGuest);
            eventGenaralGuests.setGeneralGuest(eventGearaltGuestsList);
            eventGenaralGuests = eventGenaralGuestsRepository.save(eventGenaralGuests);
            event.setEventGenaralGuests(eventGenaralGuests);
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public ResponseEntity<?> eventAdminConfirmation(int eventId,int guestId) {

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (!optionalEvent.isPresent()) {
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id. ", "Invalid Event Id.", false), HttpStatus.BAD_REQUEST);
        }

        Event event = optionalEvent.get();
        EventGenaralGuests eventGenaralGuests = event.getEventGenaralGuests();
        List<GeneralGuest> generalGuestList = eventGenaralGuests.getGeneralGuest();

        boolean status = false;
        for (GeneralGuest guest : generalGuestList) {
            if (guest.getGenaralGuestId() == guestId) {

                guest.setEventAdminConfirmation(true);
                try {
                    generalGuestRepository.save(guest);
                    status = true;
                } catch (Exception e) {
                    throw new RuntimeException("Something went wrong");
                }
            }
        }

        if (status) {
            return new ResponseEntity<>(new ResponseModel("Confirmed Successfully", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseModel("User Does not exists", false), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> eventAdminConfirmation(EventGenaralConfirmationDto eventGenaralConfirmationDto){

        Optional<Event> optionalEvent = eventRepository.findById(eventGenaralConfirmationDto.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id. ","Invalid Event Id.",false), HttpStatus.BAD_REQUEST);
        }

        Event event = optionalEvent.get();
        EventGenaralGuests eventGenaralGuests = event.getEventGenaralGuests();
        List<GeneralGuest> generalGuestList = eventGenaralGuests.getGeneralGuest();

        boolean status = false;
        for(GeneralGuest guest : generalGuestList){

            for(int i :eventGenaralConfirmationDto.getGenaralGuestEmails()) {
                if (guest.getGenaralGuestId() == i) {

                    guest.setEventAdminConfirmation(true);
                    try {
                        generalGuestRepository.save(guest);
                        status = true;
                    } catch (Exception e) {
                        throw new RuntimeException("Something went wrong");
                    }
                }
            }
        }

        if(status){
            return new ResponseEntity<>(new ResponseModel("Confirmed Successfully",true),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseModel("User Does not exists",false),HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> getEventGeneralGuestList(int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id. ","Invalid Event Id.",false), HttpStatus.BAD_REQUEST);
        }
        List<GeneralGuest>  generalGuestList = new ArrayList<>();
        Event event = optionalEvent.get();

        if(event.getEventGenaralGuests() != null){
            generalGuestList = event.getEventGenaralGuests().getGeneralGuest();
        } else {
            return new ResponseEntity<>(new ResponseModel("No events display","No events display",false),HttpStatus.BAD_REQUEST);
        }

        if(!generalGuestList.isEmpty()){
            return new ResponseEntity<>(generalGuestList,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseModel("No events display","No events display",false),HttpStatus.BAD_REQUEST);
        }

    }

}
