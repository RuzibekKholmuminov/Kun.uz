package com.example.controller;

import com.example.dto.RegionDto;
import com.example.enums.ProfileRole;
import com.example.service.RegionService;
import com.example.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping({"/private/"})
    public ResponseEntity<Integer> create(@RequestBody RegionDto dto,
                                          HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer prtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(regionService.create(dto, prtId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @RequestBody RegionDto regionDto) {
        return ResponseEntity.ok(regionService.update(id, regionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(regionService.deleteById(id));
    }

    @GetMapping("/list-paging")
    public ResponseEntity<Page<RegionDto>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "2") int size) {
        return ResponseEntity.ok(regionService.getAll(page, size));
    }
}
