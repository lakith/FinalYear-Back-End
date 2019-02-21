package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.model.EventType;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.repository.EventTypeRepository;
import com.finalproj.finalproject.service.EventTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EventTypesServiceImpl implements EventTypesService {

    @Autowired
    EventTypeRepository eventTypeRepository;

    @Override
    public ResponseEntity<?> saveEventType(EventType eventType) throws Exception {
        try {
            eventType = eventTypeRepository.save(eventType);
            return new ResponseEntity<>(eventType, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateEventType(EventType eventType, int eventTypeId) throws Exception {

        Optional<EventType> olEventTypeOpt =  eventTypeRepository.findById(eventTypeId);
        EventType olEventType = olEventTypeOpt.get();
        olEventType.setEventTypeName(eventType.getEventTypeName());



        try {
            eventType = eventTypeRepository.save(olEventType);;
            return new ResponseEntity<>(eventType, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> deleteEventType(int eventTypeId) throws Exception {
        try {
            eventTypeRepository.deleteById(eventTypeId);
            return new ResponseEntity<>(new ResponseModel("event type deleted successfullly","event type deleted successfullly",true), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
