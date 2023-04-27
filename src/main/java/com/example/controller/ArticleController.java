package com.example.controller;

import com.example.dto.ArticleDto;
import com.example.dto.JwtDto;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.service.AuthService;
import com.example.util.JwtUtil;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<ArticleDto> createArticle(@RequestBody ArticleDto articleDto,
                                                    @RequestHeader("Authorization") String authorization){
        JwtDto jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.createArticle(articleDto,jwtDTO.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteArticle(@PathVariable("id")Integer id,
                                                    @RequestHeader("Authorization") String authorization){
        JwtDto jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.deleteArticle(id,jwtDTO.getId()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateArticle(@PathVariable("id")Integer id,
                                                 @RequestHeader("Authorization") String authorization){
        JwtDto jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.updateArticle(id,jwtDTO.getId()));
    }
}
