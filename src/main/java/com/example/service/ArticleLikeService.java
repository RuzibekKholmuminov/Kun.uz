package com.example.service;

import com.example.dto.ArticleLikeDto;
import com.example.dto.CommentLikeDto;
import com.example.entity.ArticleLikeEntity;
import com.example.entity.CommentLikeEntity;
import com.example.enums.ArticleLikeStatus;
import com.example.enums.CommentLikeStatus;
import com.example.repository.ArticleLikeRepository;
import com.example.repository.CommentLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleLikeService {
    @Autowired
    private ArticleLikeRepository articleLikeRepository;
    public ArticleLikeDto articleLike(ArticleLikeDto articleLikeDto) {
        ArticleLikeEntity articleLike = articleLikeRepository.getByProfile_id(articleLikeDto.getProfile_id());
        if (articleLike != null){
            if (articleLike.getStatus() == ArticleLikeStatus.DISLIKE) {
                articleLikeRepository.updateStatus(ArticleLikeStatus.LIKE);
                articleLike.setStatus(ArticleLikeStatus.LIKE);
                articleLikeDto.setId(articleLike.getId());
                articleLikeDto.setStatus(articleLike.getStatus());
                return articleLikeDto;
            }
            if (articleLike.getStatus() == ArticleLikeStatus.LIKE){
                deleteLike(articleLikeDto);
                return null;
            }
        }
        ArticleLikeEntity articleLikeEntity = new ArticleLikeEntity();
        articleLikeEntity.setProfile_id(articleLikeDto.getProfile_id());
        articleLikeEntity.setArticle_id(articleLikeDto.getArticle_id());
        articleLikeEntity.setStatus(ArticleLikeStatus.LIKE);
        articleLikeRepository.save(articleLikeEntity);
        articleLikeDto.setId(articleLikeEntity.getId());
        articleLikeDto.setStatus(articleLikeEntity.getStatus());
        return articleLikeDto;
    }

    public ArticleLikeDto articleDislike(ArticleLikeDto articleLikeDto) {
        ArticleLikeEntity articleLike = articleLikeRepository.getByProfile_id(articleLikeDto.getProfile_id());
        if (articleLike != null){
            if (articleLike.getStatus() == ArticleLikeStatus.LIKE) {
                articleLikeRepository.updateStatus(ArticleLikeStatus.DISLIKE);
                articleLike.setStatus(ArticleLikeStatus.LIKE);
                articleLikeDto.setId(articleLike.getId());
                articleLikeDto.setStatus(articleLike.getStatus());
                return articleLikeDto;
            }
            if (articleLike.getStatus() == ArticleLikeStatus.DISLIKE){
                articleLikeRepository.delete(articleLike);
                return null;
            }
        }
        ArticleLikeEntity articleLikeEntity = new ArticleLikeEntity();
        articleLikeEntity.setProfile_id(articleLikeDto.getProfile_id());
        articleLikeEntity.setArticle_id(articleLikeDto.getArticle_id());
        articleLikeEntity.setStatus(ArticleLikeStatus.DISLIKE);
        articleLikeRepository.save(articleLikeEntity);
        articleLikeDto.setId(articleLikeEntity.getId());
        articleLikeDto.setStatus(articleLikeEntity.getStatus());
        return articleLikeDto;
    }

    public Boolean deleteLike(ArticleLikeDto articleLikeDto) {
        ArticleLikeEntity articleLikeEntity = articleLikeRepository.getByProfile_id(articleLikeDto.getProfile_id());
        if (articleLikeEntity != null){
            articleLikeRepository.delete(articleLikeEntity);
        }
        return true;
    }
}
