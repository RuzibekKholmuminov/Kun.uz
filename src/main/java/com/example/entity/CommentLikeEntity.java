package com.example.entity;

import com.example.enums.CommentLikeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment_like")
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "comment_id")
    private String commentId;
    @ManyToOne
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private CommentEntity comment;
    @Column(name = "profile_id")
    private Integer profile_id;
    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CommentLikeStatus commentLikeStatus;
}
