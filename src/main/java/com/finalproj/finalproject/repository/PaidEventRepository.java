package com.finalproj.finalproject.repository;

import com.finalproj.finalproject.model.PaidEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaidEventRepository extends JpaRepository <PaidEvent,Integer> {
}
