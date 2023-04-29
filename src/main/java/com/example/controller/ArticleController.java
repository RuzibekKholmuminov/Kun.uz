package com.example.controller;

import com.example.dto.ArticleDto;
import com.example.dto.ArticleRequestDto;
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
    public ResponseEntity<ArticleRequestDto> createArticle(@RequestBody ArticleRequestDto articleDto,
                                                    @RequestHeader("Authorization") String authorization){
        JwtDto jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.create(articleDto,jwtDTO.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteArticle(@PathVariable("id")Integer id,
                                                    @RequestHeader("Authorization") String authorization){
        JwtDto jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleRequestDto> updateArticle(@RequestBody ArticleRequestDto articleDto,
                                                 @PathVariable("id")Integer id,
                                                 @RequestHeader("Authorization") String authorization){
        JwtDto jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(articleDto, id));
    }
}
