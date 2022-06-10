package com.example.final_project_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventDTO {
    private String description;
    private String starting_date;
    private String starting_time;
    private String ending_date;
    private String ending_time;
}
