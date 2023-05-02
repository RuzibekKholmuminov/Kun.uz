package com.example.service;

import com.example.dto.ArticleFullInfoDTO;
import com.example.dto.ArticleListGet;
import com.example.dto.ArticleRequestDto;
import com.example.dto.ArticleShortInfoDTO;
import com.example.entity.*;
import com.example.enums.ArticleStatus;
import com.example.exps.ItemNotFoundException;
import com.example.mapper.ArticleShortInfoMapper;
import com.example.repository.ArticleRepository;
import com.example.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final AttachService attachService;


    public ArticleRequestDto create(ArticleRequestDto dto, Integer moderId) {
        // check

        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setModeratorId(moderId);
        entity.setRegionId(dto.getRegionId());
        entity.setAttachId(dto.getAttachId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setArticleTypeId(dto.getArticleTypeId());
        // type
        articleRepository.save(entity);
        return dto;
    }


    public ArticleRequestDto update(ArticleRequestDto dto, String id) {
        ArticleEntity entity = get(id);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        articleRepository.save(entity);
        return dto;
    }

    public boolean delete(String id) {
        ArticleEntity entity = articleRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new RuntimeException("this article is null");
        }
        entity.setVisible(false);
        articleRepository.save(entity);
        return true;
    }

    public Boolean changeStatus(ArticleStatus status, String id, Integer prtId) {
        ArticleEntity entity = get(id);
        if (status.equals(ArticleStatus.PUBLISHED)) {
            entity.setPublishedDate(LocalDateTime.now());
            entity.setPublisherId(prtId);
        }
        entity.setStatus(status);
        articleRepository.save(entity);
        return true;
    }

    public ArticleEntity DTOToEntity(ArticleRequestDto dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setContent(dto.getContent());
        entity.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        entity.setDescription(dto.getDescription());
//        entity.setSharedCount(dto.getSharedCount());
        return entity;
    }
    public List<ArticleShortInfoDTO> getLast5ByTypeId(Integer typeId) {
        List<ArticleShortInfoMapper> entityList = articleRepository.find5ByTypeIdNative(typeId, ArticleStatus.PUBLISHED.name(), 5);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }
    public ArticleShortInfoDTO toArticleShortInfo(ArticleShortInfoMapper entity) {
        ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublished_date());
        dto.setAttach(attachService.getAttachLink(entity.getAttachId()));
        return dto;
    }

    public ArticleShortInfoDTO toArticleShortInfo(ArticleEntity entity) {
        ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setAttach(attachService.getAttachLink(entity.getAttachId()));
        return dto;
    }


    public ArticleEntity get(String id) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Item not found: " + id);
        }
        return optional.get();
    }

    public List<ArticleShortInfoDTO> getLast3ByTypeId(Integer id) {
        List<ArticleShortInfoMapper> entityList = articleRepository.find3ByTypeIdNative(id, ArticleStatus.PUBLISHED.name(), 3);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }

//    public List<ArticleShortInfoDTO> getLast8ByTypeId(Integer type_id, ArticleListGet article_id) {
//        List<ArticleShortInfoMapper> entityList = articleRepository.find8ByTypeIdNative(type_id, article_id.getArticle_id(), ArticleStatus.PUBLISHED.name());
//        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
//        entityList.forEach(entity -> {
//            dtoList.add(toArticleShortInfo(entity));
//        });
//        return dtoList;
//    }

    public ArticleFullInfoDTO getById(String id) {
        ArticleEntity articleEntity = articleRepository.getById(id);
        ArticleFullInfoDTO articleFullInfoDTO = new ArticleFullInfoDTO();
        articleFullInfoDTO.setTitle(articleEntity.getTitle());
        articleFullInfoDTO.setDescription(articleEntity.getDescription());
        articleFullInfoDTO.setContent(articleEntity.getContent());
        articleFullInfoDTO.setArticleTypeId(articleEntity.getArticleTypeId());
        articleFullInfoDTO.setAttachId(articleEntity.getAttachId());
        articleFullInfoDTO.setCategoryId(articleEntity.getCategoryId());

        return articleFullInfoDTO;
    }
}
