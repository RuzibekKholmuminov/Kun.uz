package com.example.dto;

import com.example.enums.GeneralStatus;
import com.example.enums.ProfileRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private Integer id;
    @NotNull(message = "name required")
    private String name;
    @NotNull(message = "surname required")
    private String surname;
    @NotNull(message = "email required")
    private String email;
    @NotNull(message = "phone required")
    private String phone;
    @NotNull(message = "role required")
    private String password;
    @NotNull(message = "status required")
    private GeneralStatus status;
    @NotNull(message = "role required")
    private ProfileRole role;
    private Boolean visible;
}
