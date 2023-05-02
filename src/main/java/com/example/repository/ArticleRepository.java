package com.example.repository;

import com.example.entity.ArticleEntity;
import com.example.entity.ArticleTypeEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.enums.ArticleStatus;
import com.example.mapper.ArticleShortInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String>, Repository<ArticleEntity, String> {
    List<ArticleEntity> findTop5ByArticleTypeEntityIdAndStatusAndVisibleOrderByCreatedDateDesc(Integer typeId, ArticleStatus articleStatus, boolean b);
    @Query("SELECT new ArticleEntity(id, title, description, attachId, publishedDate) From ArticleEntity where status =:status and visible = true and articleTypeId =:typeId order by createdDate desc limit 5")
    List<ArticleEntity> find5ByTypeId(@Param("typeId") Integer typeId, @Param("status") ArticleStatus status);

    @Query(value = "SELECT a.id id,a.title title ,a.description description,a.attach_id attachId,a.published_date published_date" +
            " FROM article AS a  where  a.article_type_id =:type_id and status =:status order by created_date desc Limit :limit",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find5ByTypeIdNative(@Param("type_id") Integer type_id,
                                                     @Param("status") String status,
                                                     @Param("limit") Integer limit);

    @Query(value = "SELECT a.id id,a.title title ,a.description description,a.attach_id attachId,a.published_date published_date" +
            " FROM article AS a  where  a.article_type_id =:type_id and status =:status order by created_date desc Limit :limit",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find3ByTypeIdNative(@Param("type_id") Integer type_id,
                                                     @Param("status") String name,
                                                     @Param("limit") Integer limit);

    ArticleEntity getById(String id);

}
