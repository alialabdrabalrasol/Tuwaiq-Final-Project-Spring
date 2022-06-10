package com.example.final_project_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor @Data
public class GalleryUserDTO {
    private Integer gallery_id;
    private Integer user_id;
    private String desciption;
    private String photo_url;
    private LocalDateTime pic_date;
}
