package com.example.entity;

import com.example.enums.ArticleLikeStatus;
import com.example.enums.CommentLikeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "article_like")
public class ArticleLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "article_id")
    private String article_id;
    @ManyToOne
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    private ArticleEntity article;
    @Column(name = "profile_id")
    private Integer profile_id;
    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ArticleLikeStatus status;
}
