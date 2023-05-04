package com.example.repository;

import com.example.entity.ArticleLikeEntity;
import com.example.entity.CommentLikeEntity;
import com.example.enums.ArticleLikeStatus;
import com.example.enums.CommentLikeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ArticleLikeRepository extends CrudRepository<ArticleLikeEntity, Integer>{
    ArticleLikeEntity getByProfile_id(Integer id);
    @Transactional
    @Modifying
    @Query("update ArticleLikeEntity as a set a.status = :status ")
    Integer updateStatus(@Param("status") ArticleLikeStatus articleLikeStatus);
}
