package com.finalproj.finalproject.service.impl;

import com.finalproj.finalproject.dto.PeymentPackageDTO;
import com.finalproj.finalproject.model.PeymantPackage;
import com.finalproj.finalproject.model.ResponseModel;
import com.finalproj.finalproject.repository.PeymentPackageRepository;
import com.finalproj.finalproject.service.PeymentPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeymentPackageServiceimpl implements PeymentPackageService {

    @Autowired
    PeymentPackageRepository peymentPackageRepository;

    @Override
    @SuppressWarnings("Duplicates")
    public ResponseEntity<?> savepeymentDetails(PeymentPackageDTO peymentPackageDTO) {

        PeymantPackage peymantPackage = new PeymantPackage();
        peymantPackage.setPeymentAmmount(peymentPackageDTO.getPeymentAmmount());
        peymantPackage.setNumberOfGuests(peymentPackageDTO.getNumberOfGuests());
        peymantPackage.setNumberOfotifications(peymentPackageDTO.getNumberOfnotifications());

        peymantPackage = peymentPackageRepository.save(peymantPackage);
        return new ResponseEntity<>(peymantPackage, HttpStatus.CREATED);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public ResponseEntity<?> updatePeymentDetails(int peymentid, PeymentPackageDTO peymentPackageDTO) {

        Optional<PeymantPackage> peymantPackageOptional = peymentPackageRepository.findById(peymentid);
        if(!peymantPackageOptional.isPresent()){
            return new ResponseEntity<>(new ResponseModel("invalied peyment package id",false),HttpStatus.BAD_REQUEST);
        }
        PeymantPackage peymantPackage = peymantPackageOptional.get();
        peymantPackage.setPeymentAmmount(peymentPackageDTO.getPeymentAmmount());
        peymantPackage.setNumberOfGuests(peymentPackageDTO.getNumberOfGuests());
        peymantPackage.setNumberOfotifications(peymentPackageDTO.getNumberOfnotifications());

        peymantPackage = peymentPackageRepository.save(peymantPackage);
        return new ResponseEntity<>(peymantPackage, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAllPeymentDetails() {
        List<PeymantPackage> peymantPackages = new ArrayList<>();
        if(peymentPackageRepository.findAll().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(peymantPackages,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getOnePeymentDetails(int peymentId) {
        Optional<PeymantPackage> peymantPackageOptional = peymentPackageRepository.findById(peymentId);
        if(!peymantPackageOptional.isPresent()){
            return new ResponseEntity<>(new ResponseModel("invalied peyment package id",false),HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(peymantPackageOptional.get(),HttpStatus.OK);
        }
    }
}
