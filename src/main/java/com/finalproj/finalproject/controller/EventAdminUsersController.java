package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.EventAdminUsers;
import com.finalproj.finalproject.service.EventAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event-admins")
@CrossOrigin
public class EventAdminUsersController {

    @Autowired
    EventAdminService eventAdminService;

    @GetMapping("/test")
    public ResponseEntity test(){
        EventAdminUsers eventAdminUsers = new EventAdminUsers();
        eventAdminUsers.setEventId(1);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        eventAdminUsers.setAdminUsers(integerList);
        return new ResponseEntity(eventAdminUsers, HttpStatus.OK);
    }

    @PostMapping("/save-admins")
    public ResponseEntity saveEventAdmins(@RequestBody @Valid EventAdminUsers eventAdminUsers) throws Exception {
        return eventAdminService.addNewAdminUsers(eventAdminUsers);
    }

    @GetMapping("/get-all-admins")
    public ResponseEntity getAllAdmins(@RequestParam("event-id") int eventId){
        return eventAdminService.getAllEventAdmins(eventId);
    }

}
