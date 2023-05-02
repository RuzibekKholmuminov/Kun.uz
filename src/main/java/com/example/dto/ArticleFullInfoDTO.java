package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleFullInfoDTO {
    private String title;
    private String description;
    private String content;
    private String attachId;
    private Integer regionId;
    private Integer categoryId;
    private Integer articleTypeId;
}
