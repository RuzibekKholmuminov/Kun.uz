package com.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CommentDto {
    private String id;
    @NotNull(message = "content required")
    private String content;
    @NotNull(message = "profile_id required")
    private Integer profile_id;
    @NotNull(message = "article_id required")
    private String article_id;
    private LocalDate createdDate;
    private LocalDate update_date;
    private Boolean visible = true;
    private Integer likeCount;
}
