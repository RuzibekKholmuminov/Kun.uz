package com.example.controller;

import com.example.dto.ArticleFullInfoDTO;
import com.example.dto.ArticleRequestDto;
import com.example.dto.ArticleShortInfoDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/private")
    public ResponseEntity<ArticleRequestDto> create(@RequestBody @Valid ArticleRequestDto dto,
                                             HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer prtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleService.create(dto, prtId));
    }

    @PostMapping("/private/{id}")
    public ResponseEntity<ArticleRequestDto> update(@RequestBody ArticleRequestDto dto,
                                                    HttpServletRequest request,
                                                    @PathVariable("id") String articleId) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.update(dto, articleId));
    }

    @DeleteMapping("/private/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id,
                                    HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @PostMapping("/private/change-status/{id}/{status}")
    public ResponseEntity<Boolean> changeStatus(@PathVariable("id") String id,
                                          @PathVariable("status") String status,
                                                HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer prtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleService.changeStatus(ArticleStatus.valueOf(status), id, prtId));
    }

    @GetMapping("/public/getLast5Article/{id}")
    private ResponseEntity<List<ArticleShortInfoDTO>> getLast5Article(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleService.getLast5ByTypeId(id));
    }

    @GetMapping("/public/getLast3Article/{id}")
    private ResponseEntity<List<ArticleShortInfoDTO>> getLast3Article(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleService.getLast3ByTypeId(id));
    }

    @GetMapping("/public/getById/{id}")
    private ResponseEntity<ArticleFullInfoDTO> getByIdAndLang(@PathVariable("id") String  id){
        return ResponseEntity.ok(articleService.getById(id));
    }

}
