package com.finalproj.finalproject.repository;

import com.finalproj.finalproject.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository <EventType,Integer> {
}
