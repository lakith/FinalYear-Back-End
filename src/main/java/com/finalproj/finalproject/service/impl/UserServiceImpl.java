package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.configuration.ApiParameters;
import com.finalproj.finalproject.dto.*;
import com.finalproj.finalproject.jwt.JwtGenerator;
import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.model.User;
import com.finalproj.finalproject.model.UserRole;
import com.finalproj.finalproject.repository.EventRepository;
import com.finalproj.finalproject.repository.UserRepository;
import com.finalproj.finalproject.repository.UserRoleRepository;
import com.finalproj.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder() ;

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    private EventRepository eventRepository;



    @Override
    public ResponseEntity<?> saveNewUser(UserDTO userDTO) throws Exception {
        ResponseModel responseModel = new ResponseModel();
        Optional<User> userOpt = userRepository.getUserByUsernameForSignUp(userDTO.getUsername());
        if(userOpt.isPresent()){
            responseModel.setMessage("Username Already Exists!");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
        if(userRepository.getUserByEmail(userDTO.getEmail()).isPresent()){
            responseModel.setMessage("Email Already Exists!");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
        }

        Optional<UserRole> optionalUserRole = userRoleRepository.findById(userDTO.getRoleId());

        if(!optionalUserRole.isPresent()){
            responseModel.setMessage("Invalid User Role");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
        }

        if(userDTO.getProfilePic().isEmpty()){
            responseModel.setMessage("Please Upload a profile pic");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
        }

        String profilePic = amazonClient.uploadFile(userDTO.getProfilePic(),true);

        String encryptedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setUserRole(optionalUserRole.get());
        newUser.setPassword(encryptedPassword);
        newUser.setProfilePic(profilePic);



        try {
            userRepository.save(newUser);
            responseModel.setMessage("User Added Successfully!");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public ResponseEntity<?> getUserFromToken(Principal principal){
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(principal.getName()));
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied user id","Invalied user id",false),HttpStatus.BAD_REQUEST);
        } else {
            String profileUrl = amazonClient.getUrlFromFileName(optionalUser.get().getProfilePic());
            User user = optionalUser.get();

            AuthToken authToken = new AuthToken();
            authToken.setAccessToken(principal.getName());
            authToken.setRefreshToken(user.getRefeshToken());

            DisplayUserDTO displayUserDTO = new DisplayUserDTO();
            displayUserDTO.setName(user.getName());
            displayUserDTO.setUserName(user.getUsername());
            displayUserDTO.setProfileUrl(profileUrl);
            displayUserDTO.setEmail(user.getEmail());
            displayUserDTO.setUserId(user.getUserId());
            displayUserDTO.setAuthToken(authToken);
            displayUserDTO.setUserRole(user.getUserRole());

            return new ResponseEntity<>(displayUserDTO,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> userLogin(LoginDTO loginDTO) {
        ResponseModel responseModel = new ResponseModel();
        Optional<User> optionalUser = userRepository.getUserByUsername(loginDTO.getUsername());
        if(!optionalUser.isPresent()){
            responseModel.setMessage("Invalid Login Credentials OR User Is Not Active");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.UNAUTHORIZED);
        }

        if(bCryptPasswordEncoder.matches(loginDTO.getPassword(), optionalUser.get().getPassword())) {
            String accessToken = createJWtWithOutPrefix(optionalUser.get());
            if (accessToken == null) {
                responseModel.setMessage("Something went Wrong");
                responseModel.setStatus(false);
                return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String refreshToken = createRefreshToken(optionalUser.get());
            if (refreshToken == null) {
                responseModel.setMessage("Something went Wrong");
                responseModel.setStatus(false);
                return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            AuthToken authToken = new AuthToken();
            authToken.setAccessToken(accessToken);
            authToken.setRefreshToken(refreshToken);

            String profileUrl = amazonClient.getUrlFromFileName(optionalUser.get().getProfilePic());

            DisplayUserDTO displayUserDTO = new DisplayUserDTO();
            displayUserDTO.setAuthToken(authToken);
            displayUserDTO.setEmail(optionalUser.get().getEmail());
            displayUserDTO.setUserId(optionalUser.get().getUserId());
            displayUserDTO.setName(optionalUser.get().getName());
            displayUserDTO.setUserName(optionalUser.get().getUsername());
            displayUserDTO.setUserRole(optionalUser.get().getUserRole());
            displayUserDTO.setProfileUrl(profileUrl);

            return new ResponseEntity<>(displayUserDTO, HttpStatus.OK);
            } else {
                responseModel.setMessage("Invalid Password Entered");
                responseModel.setStatus(false);
                return new ResponseEntity<>(responseModel,HttpStatus.UNAUTHORIZED);
            }

    }

    public ResponseEntity<?> getMyEvents(Principal principal) throws ParseException {
        Optional<User> optionalUser =  userRepository.findById(Integer.parseInt(principal.getName()));
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid User Id.","Invalid User Id.",false),HttpStatus.BAD_REQUEST);
        }
        List<Event> eventList = new ArrayList<>();
        eventList = optionalUser.get().getEventList();

        if(eventList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EventDisplayDTO> eventDisplayDTOS = new ArrayList<>();

        for (Event event: eventList ) {
            String eventUrl = amazonClient.getUrlFromFileName(event.getEventThumbnail());
            EventDisplayDTO eventDisplayDTO = new EventDisplayDTO();
            eventDisplayDTO.setEventId(event.getEventId());
            eventDisplayDTO.setEventCreators(event.getEventCreators());
            eventDisplayDTO.setEventType(event.getEventType());
            eventDisplayDTO.setEventName(event.getEventName());
            eventDisplayDTO.setEventThumbnail(eventUrl);
            eventDisplayDTO.setEventStartDate(event.getEventStartDate());
            eventDisplayDTO.setEventEndDate(event.getEventEndDate());
            eventDisplayDTO.setEventPlace(event.getEventPlace());
            eventDisplayDTO.setEventHostedUrl(event.getEventHostedUrl());
            eventDisplayDTO.setNumberOfGuests(event.getNumberOfGuests());
            eventDisplayDTO.setPaidEvent(event.isPaidEvent());
            eventDisplayDTO.setPaidEventData(event.getPaidEventData());
            eventDisplayDTO.setFreeEvent(event.isFreeEvent());
            eventDisplayDTO.setEventPrivate(event.isEventPrivate());
            eventDisplayDTO.setEventPublic(event.isEventPublic());
            eventDisplayDTO.setEventSpecialGuests(event.getEventSpecialGuests());
            eventDisplayDTO.setSpecialGuestEmails(event.getSpecialGuestEmails());
            eventDisplayDTO.setGenaralGuestMails(event.getGenaralGuestMails());
            eventDisplayDTO.setEventFrontPage(event.getEventFrontPage());
            eventDisplayDTO.setEventComments(event.getEventComments());

            Date eventDate = null;
            Date today = null;
            try {
                eventDate = event.getEventEndDate();
                today = Calendar.getInstance().getTime();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                eventDate = sdf.parse(sdf.format(eventDate));
                today = sdf.parse(sdf.format(today));
            } catch (ParseException e) {
                throw e;
            }

            if (eventDate.compareTo(today) < 0) {
                    eventDisplayDTO.setClosed(true);
            }

            eventDisplayDTOS.add(eventDisplayDTO);
        }

        return new ResponseEntity<>(eventDisplayDTOS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<DisplayUserDataDTO> userDataDTOList = new ArrayList<>();
            for(User user : userList){
                DisplayUserDataDTO displayUserDataDTO = new DisplayUserDataDTO();
                displayUserDataDTO.setUserId(user.getUserId());
                displayUserDataDTO.setName(user.getName());
                displayUserDataDTO.setEmail(user.getEmail());
                displayUserDataDTO.setActive(user.isActive());
                displayUserDataDTO.setUsername(user.getUsername());

                String profilePic = amazonClient.getUrlFromFileName(user.getProfilePic());

                displayUserDataDTO.setProfilePic(profilePic);

                userDataDTOList.add(displayUserDataDTO);
            }

            return new ResponseEntity<>(userDataDTOList,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> activateAUser(int userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid User Id","Invalid User Id",false),HttpStatus.BAD_REQUEST);
        } else{
            User user = optionalUser.get();
            user.setActive(true);
            userRepository.save(user);
            return new ResponseEntity<>(new ResponseModel("User Activated successfully","User Activated successfully",true),HttpStatus.OK);
        }
    }


    private String createJWtWithOutPrefix(User user) {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+user.getUserRole().getRoleType()));
        String accessToken = null;
        try {
            accessToken = JwtGenerator.genarateAccessJWT( user.getUsername(),Integer.toString(user.getUserId()), grantedAuthorities, ApiParameters.JWT_EXPIRATION, ApiParameters.JWT_SECRET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    private String createRefreshToken(User user) {
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().getRoleType()));
        String refreshToken = JwtGenerator.generateRefreshToken(user.getUsername(),Integer.toString(user.getUserId()),grantedAuthorityList,ApiParameters.REFRESH_TOKEN_EXPIRATION,ApiParameters.JWT_SECRET);
        try {
            int i = userRepository.updateRefreshToken(user.getUsername(), refreshToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return refreshToken;
    }

}
