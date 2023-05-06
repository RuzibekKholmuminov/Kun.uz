package com.example.dto;

import com.example.enums.ArticleLikeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleLikeDto {
    private Integer id;
    private String article_id;
    private Integer profile_id;
    private ArticleLikeStatus status;
}
