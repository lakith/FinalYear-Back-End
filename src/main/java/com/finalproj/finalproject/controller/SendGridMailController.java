package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.service.impl.SendGridMailClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@CrossOrigin
public class SendGridMailController {

    @GetMapping("/send")
    public ResponseEntity<?> mailSend() throws Exception {
        SendGridMailClient.sendMail("body","lakith","event");
        return new ResponseEntity<>("sent", HttpStatus.OK);
    }

}
