package com.example.service;

import com.example.dto.ArticleRequestDto;
import com.example.entity.ArticleEntity;
import com.example.enums.ArticleStatus;
import com.example.repository.ArticleRepository;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ArticleRequestDto create(ArticleRequestDto dto, Integer moderId) {
        // check
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());

        // type
        articleRepository.save(entity);
        return dto;
    }

    public ArticleRequestDto update(ArticleRequestDto dto, Integer id) {
        ArticleEntity entity = articleRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new RuntimeException("this article is null");
        }
        if (dto.getCategoryId() != null) {
            entity.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getContent() != null) {
            entity.setContent(dto.getContent());
        }
        if (dto.getTitle() != null) {
            entity.setTitle(dto.getTitle());
        }
        articleRepository.save(entity);
        return dto;
    }

    public boolean delete(Integer id) {
        ArticleEntity entity = articleRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new RuntimeException("this article is null");
        }
        entity.setVisible(false);
        articleRepository.save(entity);
        return true;
    }

    public String changeStatus(ArticleStatus status,Integer id) {
        ArticleEntity entity =articleRepository.findById(id).orElse(null);
        if (entity==null){
            throw new RuntimeException("entity is null");
        }
        entity.setStatus(status);
        articleRepository.save(entity);
        return "changed !!! ";
    }

    public ArticleEntity DTOToEntity(ArticleRequestDto dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setContent(dto.getContent());
        entity.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
