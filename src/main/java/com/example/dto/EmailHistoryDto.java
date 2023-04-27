package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmailHistoryDto {
    private Integer id;
    private String message;
    private String email;
    private LocalDate createdDate;
}
