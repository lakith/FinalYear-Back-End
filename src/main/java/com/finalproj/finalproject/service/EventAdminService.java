package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.EventAdminUsers;
import org.springframework.http.ResponseEntity;

public interface EventAdminService {

    ResponseEntity<?> addNewAdminUsers(EventAdminUsers eventAdminUsers) throws Exception;

    ResponseEntity<?> getAllEventAdmins(int eventId);
}
