package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.EventFrontPageDTO;
import com.finalproj.finalproject.service.EventFrontPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;

@RestController
@RequestMapping("/event-front-page")
@CrossOrigin
public class EventFrontPageController {

    @Autowired
    EventFrontPageService eventFrontPageService;


    @PostMapping("/save")
    public ResponseEntity saveFrontpageData(
            @RequestParam MultipartFile frontImage,
            @RequestParam(required = true)  String content,
            @RequestParam(required = true)  String termsAndConditions,
            @RequestParam(required = true)  String otherDetails,
            @RequestParam(required = true)  int eventID,
            Principal principal
    ) throws Exception {
        EventFrontPageDTO eventFrontPageDTO = new EventFrontPageDTO();
        eventFrontPageDTO.setContent(content);
        eventFrontPageDTO.setFrontImage(frontImage);
        eventFrontPageDTO.setTermsAndConditions(termsAndConditions);
        eventFrontPageDTO.setOtherDetails(otherDetails);
        eventFrontPageDTO.setEventID(eventID);

        return eventFrontPageService.saveEventFrontPageDetails(eventFrontPageDTO,principal);
    }

    @GetMapping("/get-details")
    public ResponseEntity getFrontPageDetails(@RequestParam("event-id") int eventId) {
        return eventFrontPageService.getFrontPgeDetails(eventId);
    }

}
