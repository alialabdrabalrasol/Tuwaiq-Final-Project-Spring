package com.example.final_project_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class UserEventDTO {
    private Integer user_id;
    private Integer event_id;
}
