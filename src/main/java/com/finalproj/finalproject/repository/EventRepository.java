package com.finalproj.finalproject.repository;

import com.finalproj.finalproject.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {

    @Query("SELECT e FROM Event e WHERE e.eventPrivate = true")
    List<Event> getAllPrivateEvents ();

    @Query("SELECT e FROM Event e WHERE e.eventPublic = true")
    List<Event> getAllPublicEvents ();

    @Query("SELECT e FROM Event e WHERE e.freeEvent = true")
    List<Event> getAllFreeEvents ();

    @Query("SELECT e FROM Event e WHERE e.paidEvent = true")
    List<Event> getAllPaiEvents ();

    @Query("SELECT e FROM Event e WHERE e.eventType.eventTypeId = ?1")
    List<Event> getAllEventsByEventType (int eventType);
}
