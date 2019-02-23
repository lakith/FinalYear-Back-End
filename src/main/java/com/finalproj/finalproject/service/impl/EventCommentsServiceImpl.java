package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.dto.CommentDTO;
import com.finalproj.finalproject.dto.CommentUpdateDto;
import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.model.EventComments;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.model.User;
import com.finalproj.finalproject.repository.*;
import com.finalproj.finalproject.service.EventCommentsService;
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
public class EventCommentsServiceImpl implements EventCommentsService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventCommentsRepositroy eventCommentsRepositroy;


   @Override
    public ResponseEntity<?> saveComment (CommentDTO commentDTO, Principal principal) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(commentDTO.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Event Id.","Invalied Event Id",false), HttpStatus.BAD_REQUEST);
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
        eventComments.setEvent(event);
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

    @Override
    public ResponseEntity<?> updateComment(CommentUpdateDto commentUpdateDto) throws Exception{

       Optional<EventComments> eventCommentsOptional = eventCommentsRepositroy.findById(commentUpdateDto.getCommentId());
       if(!eventCommentsOptional.isPresent()){
           return new ResponseEntity<>(new ResponseModel("Invalied Comment Id","Invalied Comment Id",false),HttpStatus.BAD_REQUEST);
       }
       EventComments eventComments = eventCommentsOptional.get();
       eventComments.setComment(commentUpdateDto.getComment());

        try {
            eventComments = eventCommentsRepositroy.save(eventComments);
            return new ResponseEntity<>(eventComments,HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteComment(int commentId) throws Exception{

        Optional<EventComments> eventCommentsOptional = eventCommentsRepositroy.findById(commentId);
        if(!eventCommentsOptional.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied Comment Id.","Invalied Comment Id",false),HttpStatus.BAD_REQUEST);
        }

        try {
            eventCommentsRepositroy.deleteById(commentId);
            return new ResponseEntity<>(new ResponseModel("comment deleted successfully","comment deleted successfully",true),HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
