package com.example.service;

import com.example.dto.CommentDto;
import com.example.dto.CommentLikeDto;
import com.example.entity.CommentLikeEntity;
import com.example.enums.CommentLikeStatus;
import com.example.repository.CommentLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;
    public CommentLikeDto commentLike(CommentLikeDto commentDto) {
        CommentLikeEntity commentLike = commentLikeRepository.getByProfile_id(commentDto.getProfile_id());
        if (commentLike != null){
            if (commentLike.getCommentLikeStatus() == CommentLikeStatus.DISLIKE) {
                commentLikeRepository.updateStatus(CommentLikeStatus.LIKE);
                commentLike.setCommentLikeStatus(CommentLikeStatus.LIKE);
                commentDto.setId(commentLike.getId());
                commentDto.setStatus(commentLike.getCommentLikeStatus());
                return commentDto;
            }
            if (commentLike.getCommentLikeStatus() == CommentLikeStatus.LIKE){
                commentLikeRepository.delete(commentLike);
                return null;
            }
        }
        CommentLikeEntity commentLikeEntity = new CommentLikeEntity();
        commentLikeEntity.setComment_id(commentDto.getProfile_id());
        commentLikeEntity.setProfile_id(commentDto.getProfile_id());
        commentLikeEntity.setCommentLikeStatus(CommentLikeStatus.LIKE);
        commentLikeRepository.save(commentLikeEntity);
        commentDto.setId(commentLikeEntity.getId());
        commentDto.setStatus(commentLikeEntity.getCommentLikeStatus());
        return commentDto;
    }

    public CommentLikeDto commentDislike(CommentLikeDto commentDto) {
        CommentLikeEntity commentLike = commentLikeRepository.getByProfile_id(commentDto.getProfile_id());
        if (commentLike != null){
            if (commentLike.getCommentLikeStatus() == CommentLikeStatus.LIKE) {
                commentLikeRepository.updateStatus(CommentLikeStatus.DISLIKE);
                commentLike.setCommentLikeStatus(CommentLikeStatus.DISLIKE);
                commentDto.setId(commentLike.getId());
                commentDto.setStatus(commentLike.getCommentLikeStatus());
                return commentDto;
            }
            if (commentLike.getCommentLikeStatus() == CommentLikeStatus.DISLIKE){
                commentLikeRepository.delete(commentLike);
                return null;
            }
        }
        CommentLikeEntity commentLikeEntity = new CommentLikeEntity();
        commentLikeEntity.setComment_id(commentDto.getComment_id());
        commentLikeEntity.setProfile_id(commentDto.getProfile_id());
        commentLikeEntity.setCommentLikeStatus(CommentLikeStatus.DISLIKE);
        commentLikeRepository.save(commentLikeEntity);
        commentDto.setId(commentLikeEntity.getId());
        commentDto.setStatus(commentLikeEntity.getCommentLikeStatus());
        return commentDto;
    }

    public Boolean deleteLike(CommentLikeDto commentLikeDto) {
        CommentLikeEntity commentLike = commentLikeRepository.getByProfile_id(commentLikeDto.getProfile_id());
        if (commentLike != null){
            commentLikeRepository.delete(commentLike);
        }
        return true;
    }
}
