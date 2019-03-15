package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.dto.DisplayAdminsDTO;
import com.finalproj.finalproject.dto.EventAdminUsers;
import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.model.User;
import com.finalproj.finalproject.repository.*;
import com.finalproj.finalproject.service.EventAdminService;
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
public class EventAdminServiceImpl implements EventAdminService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AmazonClient amazonClient;

    public ResponseEntity<?> addNewAdminUsers(EventAdminUsers eventAdminUsers) throws Exception {

        Optional<Event> optionalEvent = eventRepository.findById(eventAdminUsers.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id.","Invalied Event Id.",false), HttpStatus.BAD_REQUEST);
        }
        Event event = optionalEvent.get();
        List<User> eventCreators = event.getEventCreators();

        if(eventCreators.isEmpty()){
            return new ResponseEntity<>(new ResponseModel("There must be at least one event creator","There must be at least one event creator",false),HttpStatus.BAD_REQUEST);
        }

        List<Integer> existing = new ArrayList<>();


        for(User user : event.getEventCreators()){
            existing.add(user.getUserId());
        }

        for(int i : eventAdminUsers.getAdminUsers()){
            for(int a : existing){
                if(i == a) {
                    return new ResponseEntity<>(new ResponseModel("Users are Already existing.","Users are Already existing.",false), HttpStatus.BAD_REQUEST);
                }
            }
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
            event = eventRepository.save(event);
            return new ResponseEntity<>(event,HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public ResponseEntity<?> getAllEventAdmins(int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid Event Id",false),HttpStatus.BAD_REQUEST);
        } else {
            List<User> eventAdmins = optionalEvent.get().getEventCreators();
            List<DisplayAdminsDTO> displayAdminsDTOS = new ArrayList<>();
            for(User user : eventAdmins){
                DisplayAdminsDTO displayAdminsDTO = new DisplayAdminsDTO();
                displayAdminsDTO.setUserId(user.getUserId());
                displayAdminsDTO.setName(user.getName());
                displayAdminsDTO.setUsername(user.getUsername());

                String profileUrl = amazonClient.getUrlFromFileName(user.getProfilePic());

                displayAdminsDTO.setImageUrl(profileUrl);

                displayAdminsDTOS.add(displayAdminsDTO);
            }

            return new ResponseEntity<>(displayAdminsDTOS,HttpStatus.OK);
        }
    }


}
