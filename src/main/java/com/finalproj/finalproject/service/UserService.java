package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.LoginDTO;
import com.finalproj.finalproject.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {

    ResponseEntity<?> saveNewUser(UserDTO userDTO) throws Exception;

    ResponseEntity<?> userLogin(LoginDTO loginDTO);

    ResponseEntity<?> activateAUser(int userId);

    ResponseEntity<?> getUserFromToken(Principal principal);

}
