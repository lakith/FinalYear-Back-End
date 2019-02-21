package com.finalproj.finalproject.repository;

import com.finalproj.finalproject.model.SpecialGuestEmails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialGuestMailRepository extends JpaRepository <SpecialGuestEmails,Integer> {
}
