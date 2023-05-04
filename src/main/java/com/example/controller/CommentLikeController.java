package com.example.controller;

import com.example.dto.CommentDto;
import com.example.dto.CommentLikeDto;
import com.example.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment-like")
public class CommentLikeController {
    @Autowired
    private CommentLikeService commentLikeService;

    @PostMapping("/like")
    private ResponseEntity<CommentLikeDto> likeCommentLike(@RequestBody CommentLikeDto commentDto){
        return ResponseEntity.ok(commentLikeService.commentLike(commentDto));
    }

    @PostMapping("/dislike")
    private ResponseEntity<CommentLikeDto> dislikeCommentLike(@RequestBody CommentLikeDto commentDto){
        return ResponseEntity.ok(commentLikeService.commentDislike(commentDto));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Boolean> deleteLike(@RequestBody CommentLikeDto commentLikeDto){
        return ResponseEntity.ok(commentLikeService.deleteLike(commentLikeDto));
    }





}
