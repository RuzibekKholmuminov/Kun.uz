package com.example.service;

import com.example.dto.ArticleDto;
import com.example.entity.ArticleEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.enums.ArticleStatus;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleDto createArticle(ArticleDto articleDto, Integer id) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setContent(articleDto.getContent());
        articleEntity.setTitle(articleDto.getTitle());
        articleEntity.setDescription(articleDto.getDescription());
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(articleDto.getCategory_id());
        articleEntity.setCategory_id(categoryEntity);
        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(articleDto.getRegion_id());
        articleEntity.setRegion_id(regionEntity);
        articleEntity.setShared_count(articleDto.getShared_count());

        articleRepository.save(articleEntity);
        articleDto.setId(articleEntity.getId());
        return articleDto;
    }

    public boolean deleteArticle(Integer id, Integer jwt) {
        articleRepository.deleteById(id);
        return true;
    }

    public Boolean updateArticle(Integer id, Integer id1) {
        ArticleEntity articleEntity = articleRepository.getById(id);
        if (articleEntity == null){
            return false;
        }

        if (articleEntity.getArticleStatus() == ArticleStatus.PUBLISHED){
            articleEntity.setArticleStatus(ArticleStatus.NOT_PUBLISHED);
        }else if (articleEntity.getArticleStatus() == ArticleStatus.NOT_PUBLISHED){
            articleEntity.setArticleStatus(ArticleStatus.PUBLISHED);
        }
        return true;
    }
}
