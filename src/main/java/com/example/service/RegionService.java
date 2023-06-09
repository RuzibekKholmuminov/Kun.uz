package com.example.service;

import com.example.dto.RegionDto;
import com.example.entity.RegionEntity;
import com.example.exps.ItemNotFoundException;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    public Integer create(RegionDto dto, Integer adminId) {

        RegionEntity entity = new RegionEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRU());
        entity.setNameEng(dto.getNameEng());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setPrtId(adminId);
        regionRepository.save(entity); // save profile

        dto.setId(entity.getId());
        return entity.getId();
    }
    public Boolean update(Integer id, RegionDto regionDto) {
        RegionEntity entity = get(id);
        if (entity == null) {
            throw new ItemNotFoundException("Region not found");
        }
        entity.setNameUz(regionDto.getNameUz());
        entity.setNameRu(regionDto.getNameRU());
        entity.setNameEng(regionDto.getNameEng());

        regionRepository.save(entity);
        return true;
    }
    public RegionEntity get(Integer id) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Article not found: " + id);
        }
        return optional.get();
    }

    public Boolean deleteById(Integer id) {
        RegionEntity entity = get(id);
        if (entity == null) {
            throw new ItemNotFoundException("Profile not found.");
        }
        entity.setVisible(false);
        entity.setPrtId(4);
        regionRepository.save(entity);
        return true;
    }

    public Page<RegionDto> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<RegionEntity> pageObj = regionRepository.findAll(paging);

        Long totalCount = pageObj.getTotalElements();

        List<RegionEntity> entityList = pageObj.getContent();
        List<RegionDto> dtoList = new LinkedList<>();


            for (RegionEntity entity : entityList) {
                RegionDto dto = new RegionDto();
                dto.setId(entity.getId());
                dto.setNameUz(entity.getNameUz());
                dto.setNameRU(entity.getNameRu());
                dto.setNameEng(entity.getNameEng());
                dto.setCreatedDate(entity.getCreatedDate());
                dto.setVisible(entity.getVisible());
                dtoList.add(dto);
            }
            Page<RegionDto> response = new PageImpl<RegionDto>(dtoList, paging, totalCount);
            return response;
    }
}
