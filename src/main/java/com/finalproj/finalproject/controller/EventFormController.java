package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.FormConfigDTO;
import com.finalproj.finalproject.dto.FormRetriveDTO;
import com.finalproj.finalproject.model.EventForm;
import com.finalproj.finalproject.service.EventFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event-form")
public class EventFormController {

    @Autowired
    private EventFormService eventFormService;

    @PostMapping("/save-event-config")
    public ResponseEntity<?> saveEventFromConfig(@RequestBody FormRetriveDTO formRetriveDTO) throws Exception {
        return eventFormService.saveEventConfigData(formRetriveDTO);
    }

    @PostMapping("/save-event-data")
    public ResponseEntity<?> saveEventData(@RequestParam String eventData, @RequestParam int eventId){
        return eventFormService.saveEventFormData(eventId,eventData);
    }

    @GetMapping
    public ResponseEntity<?> getEventForm(@RequestParam("event-id") int eventId){
        return eventFormService.getEventForm(eventId);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        FormRetriveDTO formRetriveDTO = new FormRetriveDTO();

        FormConfigDTO formConfigDTO = new FormConfigDTO();
        formConfigDTO.setElementName("name");
        formConfigDTO.setElementType("input");
        formConfigDTO.setElementConfigType("text");
        formConfigDTO.setPlaceHolderText("Enter Your Name");
        formConfigDTO.setRequired(true);
        formConfigDTO.setRadio(false);


        FormConfigDTO formConfigDTO1 = new FormConfigDTO();
        formConfigDTO1.setElementName("age");
        formConfigDTO1.setElementType("input");
        formConfigDTO1.setElementConfigType("number");
        formConfigDTO1.setPlaceHolderText("Enter Your age");
        formConfigDTO1.setRequired(true);
        formConfigDTO1.setRadio(false);

        FormConfigDTO formConfigDTO3 = new FormConfigDTO();
        formConfigDTO3.setElementName("email");
        formConfigDTO3.setElementType("input");
        formConfigDTO3.setElementConfigType("email");
        formConfigDTO3.setPlaceHolderText("Enter Your age");
        formConfigDTO3.setRequired(true);
        formConfigDTO3.setRadio(false);

        List<FormConfigDTO> formConfigDTOS = new ArrayList<>();
        formConfigDTOS.add(formConfigDTO);
        formConfigDTOS.add(formConfigDTO1);
        formConfigDTOS.add(formConfigDTO3);

        formRetriveDTO.setFormConfigDTOS(formConfigDTOS);
        formRetriveDTO.setEventId(1);
        return new ResponseEntity<>(formRetriveDTO, HttpStatus.OK);
    }
}
