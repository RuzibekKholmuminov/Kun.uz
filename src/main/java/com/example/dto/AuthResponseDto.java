package com.example.dto;

import com.example.enums.ProfileRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDto {
    private String name;
    private String surname;
    private ProfileRole role;
    private String jwt;
}
