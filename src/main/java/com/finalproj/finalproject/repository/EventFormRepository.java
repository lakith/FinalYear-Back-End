package com.finalproj.finalproject.repository;

import com.finalproj.finalproject.model.EventForm;
import com.finalproj.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EventFormRepository  extends JpaRepository<EventForm,Integer> {

    @Query("SELECT ef FROM EventForm ef WHERE ef.event.eventId=?1")
    Optional<EventForm> getEventFormByEventData(int eventId);

}
