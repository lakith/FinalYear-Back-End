package com.finalproj.finalproject.service;

import com.finalproj.finalproject.dto.CommentDTO;
import com.finalproj.finalproject.dto.CommentUpdateDto;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface EventCommentsService {

    ResponseEntity<?> saveComment (CommentDTO commentDTO, Principal principal) throws Exception;

    ResponseEntity<?> updateComment(CommentUpdateDto commentUpdateDto) throws Exception;

    ResponseEntity<?> deleteComment(int commentId) throws Exception;

}
