package com.example.service;

import com.example.dto.CommentDto;
import com.example.entity.ArticleEntity;
import com.example.entity.CommentEntity;
import com.example.entity.ProfileEntity;
import com.example.exps.AppBadRequestException;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentDto create(CommentDto commentDto) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(commentDto.getProfile_id());

        ArticleEntity article = new ArticleEntity();
        article.setId(commentDto.getArticle_id());

        CommentEntity entity = new CommentEntity();
        entity.setContent(commentDto.getContent());
        entity.setProfile(profileEntity);
        entity.setArticle(article);
        entity.setCreatedDate(LocalDate.now());
        commentRepository.save(entity);
        commentDto.setCreatedDate(entity.getCreatedDate());
        commentDto.setId(entity.getId());
        return commentDto;
    }
}
