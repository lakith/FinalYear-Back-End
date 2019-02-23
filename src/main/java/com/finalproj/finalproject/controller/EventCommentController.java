package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.dto.CommentDTO;
import com.finalproj.finalproject.dto.CommentUpdateDto;
import com.finalproj.finalproject.service.EventCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/event-comments")
@CrossOrigin
public class EventCommentController {

    @Autowired
    EventCommentsService eventCommentsService;

    @PostMapping
    public ResponseEntity saveComment(@RequestBody @Valid CommentDTO commentDTO, Principal principal) throws Exception{
       return eventCommentsService.saveComment(commentDTO,principal);
    }

    @PutMapping
    public ResponseEntity updateComment(@RequestBody @Valid CommentUpdateDto commentUpdateDto) throws Exception {
        return eventCommentsService.updateComment(commentUpdateDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") int commentId) throws Exception {
        return eventCommentsService.deleteComment(commentId);
    }

}
