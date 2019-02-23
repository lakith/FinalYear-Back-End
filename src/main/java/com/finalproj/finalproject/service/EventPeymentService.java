package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.PaidEventDetailsDto;
import org.springframework.http.ResponseEntity;

public interface EventPeymentService {

    ResponseEntity<?> savePeymentDetails  (PaidEventDetailsDto paidEventDetailsDto) throws Exception;

}
