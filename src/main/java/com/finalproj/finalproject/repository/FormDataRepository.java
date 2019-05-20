package com.finalproj.finalproject.repository;

import com.finalproj.finalproject.model.FormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormDataRepository extends JpaRepository<FormData,Integer> {

    @Query("SELECT fd FROM FormData fd WHERE fd.eventForm.event.eventId=?1")
    List<FormData> getEventUserData(int eventId);

}
