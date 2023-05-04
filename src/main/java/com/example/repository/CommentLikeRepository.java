package com.example.repository;

import com.example.entity.CommentLikeEntity;
import com.example.enums.CommentLikeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity, Integer> {
    CommentLikeEntity getByProfile_id(Integer id);
    @Transactional
    @Modifying
    @Query("update CommentLikeEntity as a set a.commentLikeStatus = :status ")
    Integer updateStatus(@Param("status") CommentLikeStatus commentLikeStatus);
}
