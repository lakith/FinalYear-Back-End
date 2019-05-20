package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.dto.FormConfigDTO;
import com.finalproj.finalproject.dto.FormRetriveDTO;
import com.finalproj.finalproject.model.*;
import com.finalproj.finalproject.repository.EventFormRepository;
import com.finalproj.finalproject.repository.EventRepository;
import com.finalproj.finalproject.repository.FormConfigRepository;
import com.finalproj.finalproject.repository.FormDataRepository;
import com.finalproj.finalproject.service.EventFormService;
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
public class EventFormServiceImpl implements EventFormService {

    @Autowired
    EventFormRepository eventFormRepository;

    @Autowired
    FormConfigRepository formConfigRepository;

    @Autowired
    FormDataRepository formDataRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public ResponseEntity<?> saveEventConfigData(FormRetriveDTO formRetriveDTO) throws Exception {

        Optional<Event> optionalEvent = eventRepository.findById(formRetriveDTO.getEventId());
        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid event id",false), HttpStatus.BAD_REQUEST);
        }
        EventForm eventForm = new EventForm();
        Optional<EventForm> optionalEventForm = eventFormRepository.getEventFormByEventData(formRetriveDTO.getEventId());
        if(optionalEventForm.isPresent()){
            eventForm = optionalEventForm.get();
            List<FormConfig> formConfigList = eventForm.getFormConfigs();

            for(FormConfig formConfig : formConfigList){
                formConfigRepository.delete(formConfig);
            }
        }

        List<FormConfigDTO> formConfigDTOS = formRetriveDTO.getFormConfigDTOS();

        List<FormConfig> formConfigList = new ArrayList<>();

        for(FormConfigDTO formConfigDTO : formConfigDTOS){
            FormConfig formConfig = new FormConfig();
            formConfig.setElementName(formConfigDTO.getElementName());
            formConfig.setElementType(formConfigDTO.getElementType());
            formConfig.setElementConfigType(formConfigDTO.getElementConfigType());
            formConfig.setPlaceHolderText(formConfigDTO.getPlaceHolderText());
            formConfig.setRequired(formConfigDTO.isRequired());
            formConfig.setErrorMessage(formConfigDTO.getErrorMessage());
            formConfig.setRadio(formConfigDTO.isRadio());
            formConfig.setRadioGroup(formConfigDTO.getRadioGroup());

            formConfig = formConfigRepository.save(formConfig);
            formConfigList.add(formConfig);
        }
        eventForm.setEvent(optionalEvent.get());
        eventForm.setFormConfigs(formConfigList);
        try {
            eventForm =  eventFormRepository.save(eventForm);
            return new ResponseEntity<>(eventForm,HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Something went Wrong");
        }

    }

    @Override
    public ResponseEntity<?> saveEventFormData(int eventId,String eventData,String email){

        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if(!optionalEvent.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid event Data",false),HttpStatus.BAD_REQUEST);
        }

        if(optionalEvent.get().getEventForm() != null ) {
         EventForm eventForm = optionalEvent.get().getEventForm();
            /*******************************
             * TODO::Do A Form Validation
              */

            FormData formData = new FormData();
            formData.setEventForm(eventForm);
            formData.setData(eventData);
            formData.setInvitationEmail(email);
            formData =  formDataRepository.save(formData);
            return new ResponseEntity<>(formData,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseModel("Save Form Config First",false),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getEventForm(int eventId){
        Optional<EventForm> optionalEventForm= eventFormRepository.getEventFormByEventData(eventId);

        if(!optionalEventForm.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid event Data.",false),HttpStatus.BAD_REQUEST);
        }

        EventForm eventForm = optionalEventForm.get();
        return new ResponseEntity<>(eventForm,HttpStatus.OK);
    }
}
