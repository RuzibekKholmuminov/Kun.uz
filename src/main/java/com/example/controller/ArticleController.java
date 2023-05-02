package com.example.controller;

import com.example.dto.*;
import com.example.enums.ArticleStatus;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<ArticleRequestDto> create(@RequestBody @Valid ArticleRequestDto dto,
                                                    @RequestHeader("Authorization") String authorization) {
        JwtDto jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.create(dto, jwt.getId()));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ArticleRequestDto> update(@RequestBody ArticleRequestDto dto,
                                                    @RequestHeader("Authorization") String authorization,
                                                    @PathVariable("id") String articleId) {
        JwtDto jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(dto, articleId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id,
                                    @RequestHeader("Authorization") String authorization) {
        JwtDto jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @PostMapping("/change-status/{id}/{status}")
    public ResponseEntity<Boolean> changeStatus(@PathVariable("id") String id,
                                          @PathVariable("status") String status,
                                          @RequestHeader("Authorization") String authorization) {
        JwtDto jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.PUBLISHER, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.changeStatus(ArticleStatus.valueOf(status), id, jwt.getId()));
    }

    @GetMapping("/getLast5Article/{id}")
    private ResponseEntity<List<ArticleShortInfoDTO>> getLast5Article(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleService.getLast5ByTypeId(id));
    }

    @GetMapping("/getLast3Article/{id}")
    private ResponseEntity<List<ArticleShortInfoDTO>> getLast3Article(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleService.getLast3ByTypeId(id));
    }

//    @PostMapping("/getLast8Article/{id}")
//    private ResponseEntity<List<ArticleShortInfoDTO>> getLast8Article(@PathVariable("id") Integer type_id,
//                                                                      @RequestBody ArticleListGet article_id){
//        return ResponseEntity.ok(articleService.getLast8ByTypeId(type_id, article_id));
//    }

    @GetMapping("getById/{id}")
    private ResponseEntity<ArticleFullInfoDTO> getByIdAndLang(@PathVariable("id") String  id){
        return ResponseEntity.ok(articleService.getById(id));
    }

}
