package com.example.controller;

import com.example.dto.ArticleLikeDto;
import com.example.dto.CommentLikeDto;
import com.example.service.ArticleLikeService;
import com.example.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article-like")
public class ArticleLikeController {
    @Autowired
    private ArticleLikeService articleLikeService;

    @PostMapping("/like")
    private ResponseEntity<ArticleLikeDto> likeCommentLike(@RequestBody ArticleLikeDto articleLikeDto){
        return ResponseEntity.ok(articleLikeService.articleLike(articleLikeDto));
    }

    @PostMapping("/dislike")
    private ResponseEntity<ArticleLikeDto> dislikeCommentLike(@RequestBody ArticleLikeDto articleLikeDto){
        return ResponseEntity.ok(articleLikeService.articleDislike(articleLikeDto));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Boolean> deleteLike(@RequestBody ArticleLikeDto articleLikeDto){
        return ResponseEntity.ok(articleLikeService.deleteLike(articleLikeDto));
    }
}
