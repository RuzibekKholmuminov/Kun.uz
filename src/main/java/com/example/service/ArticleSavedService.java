package com.example.service;

import com.example.dto.ArticleSavedDto;
import com.example.entity.ArticleEntity;
import com.example.entity.ArticleSavedEntity;
import com.example.entity.ProfileEntity;
import com.example.repository.ArticleSavedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ArticleSavedService {
    @Autowired
    private ArticleSavedRepository articleSavedRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ArticleService articleService;
    public ArticleSavedDto create(ArticleSavedDto articleSavedDto) {
        ProfileEntity profileEntity = profileService.get(articleSavedDto.getProfile_id());
        ArticleEntity article = articleService.get(articleSavedDto.getArticle_id());

        ArticleSavedEntity articleSavedEntity = new ArticleSavedEntity();
        articleSavedEntity.setArticle(article);
        articleSavedEntity.setProfile(profileEntity);
        articleSavedEntity.setCreatedDate(LocalDate.now());
        articleSavedRepository.save(articleSavedEntity);
        return articleSavedDto;
    }

    public Boolean delete(Integer id) {
        articleSavedRepository.deleteById(id);
        return true;
    }
}
