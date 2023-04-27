package com.example.repository;

import com.example.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Integer>, Repository<ArticleEntity, Integer> {

    ArticleEntity getById(Integer id);
}
