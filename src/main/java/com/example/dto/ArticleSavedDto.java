package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ArticleSavedDto {
    private Integer id;
    private Integer profile_id;
    private String article_id;
    private LocalDate createdDate;
}
