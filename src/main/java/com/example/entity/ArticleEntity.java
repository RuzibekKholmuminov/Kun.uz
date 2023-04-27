package com.example.entity;

import com.example.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "content")
    private String content;
    @Column(name = "status")
    private ArticleStatus articleStatus = ArticleStatus.NOT_PUBLISHED;
    @Column(name = "shared_count")
    private Integer shared_count ;
    @Column(name = "image_id")
    private Integer image_id;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionEntity region_id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category_id;
}
