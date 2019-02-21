package com.finalproj.finalproject.repository;

import com.finalproj.finalproject.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Integer> {
}
