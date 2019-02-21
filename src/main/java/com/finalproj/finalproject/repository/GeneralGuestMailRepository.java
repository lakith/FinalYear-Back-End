package com.finalproj.finalproject.repository;

import com.finalproj.finalproject.model.GenaralGuestMails;
import com.finalproj.finalproject.model.GenaralGuestMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralGuestMailRepository extends JpaRepository <GenaralGuestMails,Integer> {
}
