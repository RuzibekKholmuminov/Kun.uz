package com.example.dto;

import com.example.enums.CommentLikeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeDto {
    private Integer id;
    private String comment_id;
    private Integer profile_id;
    private CommentLikeStatus status;
}
