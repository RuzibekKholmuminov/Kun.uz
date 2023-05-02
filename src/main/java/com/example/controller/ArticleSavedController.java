package com.example.controller;

import com.example.dto.ArticleSavedDto;
import com.example.service.ArticleSavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article-saved")
public class ArticleSavedController {
    @Autowired
    private ArticleSavedService articleSavedService;

    @PostMapping("/create")
    public ResponseEntity<ArticleSavedDto> create(@RequestBody ArticleSavedDto articleSavedDto){
        return ResponseEntity.ok(articleSavedService.create(articleSavedDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleSavedService.delete(id));
    }
}
