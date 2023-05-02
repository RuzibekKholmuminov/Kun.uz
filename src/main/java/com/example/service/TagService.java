package com.example.service;

import com.example.dto.TagDto;
import com.example.entity.TagEntity;
import com.example.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public TagDto create(TagDto tagDto, Integer id) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(tagDto.getName());
        tagEntity.setPrt_id(id);
        tagRepository.save(tagEntity);
        return tagDto;
    }

    public Boolean delete(Integer id) {
        tagRepository.deleteById(id);
        return true;
    }
}
