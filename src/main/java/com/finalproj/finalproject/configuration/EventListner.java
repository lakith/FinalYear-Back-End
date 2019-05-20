package com.finalproj.finalproject.configuration;

import com.finalproj.finalproject.model.PeymantPackage;
import com.finalproj.finalproject.repository.PeymentPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventListner {

    @Autowired
    PeymentPackageRepository peymentPackageRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        try{
            List<PeymantPackage> peymantPackageList = peymentPackageRepository.findAll();

            if(peymantPackageList.isEmpty() || peymantPackageList.size() != 3){

                PeymantPackage peymantPackage = new PeymantPackage();
                peymantPackage.setNumberOfotifications(150);
                peymantPackage.setNumberOfGuests(100);
                peymantPackage.setPeymentAmmount(1200);

                PeymantPackage peymantPackage1 = new PeymantPackage();
                peymantPackage1.setNumberOfotifications(300);
                peymantPackage1.setNumberOfGuests(200);
                peymantPackage1.setPeymentAmmount(1800);

                PeymantPackage peymantPackage2 = new PeymantPackage();
                peymantPackage2.setNumberOfotifications(500);
                peymantPackage2.setNumberOfGuests(300);
                peymantPackage2.setPeymentAmmount(2200);

                peymantPackage = peymentPackageRepository.save(peymantPackage);
                peymantPackage1 = peymentPackageRepository.save(peymantPackage1);
                peymantPackage2 = peymentPackageRepository.save(peymantPackage2);

                System.out.println(peymantPackage);
                System.out.println(peymantPackage1);
                System.out.println(peymantPackage2);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
