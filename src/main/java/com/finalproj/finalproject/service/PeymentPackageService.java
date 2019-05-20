package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.PeymentPackageDTO;
import org.springframework.http.ResponseEntity;

public interface PeymentPackageService {

    ResponseEntity<?> savepeymentDetails(PeymentPackageDTO peymentPackageDTO);
    ResponseEntity<?> updatePeymentDetails(int peymentid,PeymentPackageDTO peymentPackageDTO);
    ResponseEntity<?> getAllPeymentDetails();
    ResponseEntity<?> getOnePeymentDetails(int peymentId);
}
