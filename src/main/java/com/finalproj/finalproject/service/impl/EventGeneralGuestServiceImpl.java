package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.data.MailBody;
import com.finalproj.finalproject.dto.GuestMailsDTO;
import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.model.GenaralGuestMails;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.model.SpecialGuestEmails;
import com.finalproj.finalproject.repository.EventRepository;
import com.finalproj.finalproject.repository.GeneralGuestMailRepository;
import com.finalproj.finalproject.repository.UserRepository;
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
public class EventGeneralGuestServiceImpl {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GeneralGuestMailRepository generalGuestMailRepository;

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

}
