package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.PeymentPackageDTO;
import com.finalproj.finalproject.service.PeymentPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peyment-package")
@CrossOrigin
public class PeymentPackageController {

    @Autowired
    PeymentPackageService peymentPackageService;

    @PostMapping("/save")
    public ResponseEntity<?> savepeymentDetails(PeymentPackageDTO peymentPackageDTO){
        return peymentPackageService.savepeymentDetails(peymentPackageDTO);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updatePeymentDetails(int peymentid,PeymentPackageDTO peymentPackageDTO){
        return peymentPackageService.updatePeymentDetails(peymentid,peymentPackageDTO);
    }
    @PostMapping("/getAll")
    public ResponseEntity<?> getAllPeymentDetails(){
        return peymentPackageService.getAllPeymentDetails();
    }
    @PostMapping("/getOne")
    public ResponseEntity<?> getOnePeymentDetails(int peymentId){
        return peymentPackageService.getOnePeymentDetails(peymentId);
    }

}
