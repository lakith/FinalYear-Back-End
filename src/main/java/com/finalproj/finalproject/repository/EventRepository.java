package com.finalproj.finalproject.repository;

import com.finalproj.finalproject.model.Event;
import com.finalproj.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Integer> {

}
