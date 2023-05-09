package com.example.controller;

import com.example.dto.ProfileDto;
import com.example.enums.ProfileRole;
import com.example.service.ProfileService;
import com.example.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping({"", "/"})
    public ResponseEntity<ProfileDto> create(@RequestBody ProfileDto dto,
                                             HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        Integer prtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(profileService.create(dto, prtId));
    }

    @GetMapping("/list-paging")
    public ResponseEntity<Page<ProfileDto>> getAllWithPagination(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(profileService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.deleteById(id));
    }

    @PutMapping("/update/private/{id}")
    public ResponseEntity<Boolean> updateAdmin(@PathVariable ("id") Integer id,
                                               @RequestBody ProfileDto profileDto,
                                               HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.updateAdmin(id, profileDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable ("id") Integer id,
                                               @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.update(id, profileDto));
    }


}
