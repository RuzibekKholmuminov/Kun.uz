package com.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    @NotNull(message = "name required")
    private String name;
    @NotNull(message = "surname required")
    private String surname;
    @NotNull(message = "email required")
    private String email;
    @NotNull(message = "phone required")
    private String phone;
    @NotNull(message = "password required")
    private String password;
}
