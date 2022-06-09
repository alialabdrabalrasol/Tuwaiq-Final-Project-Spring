package com.example.final_project_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer event_id;
    @NotEmpty(message = "Please give a description")
    private String description;
    @NotNull(message = "Please enter a date")
    private LocalDate event_date;


    @ManyToMany(mappedBy = "events",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Place>places;

    @ManyToOne
    @JsonIgnore
    private Gallery gallery;

    @ManyToOne
    @JsonIgnore
    private User user;
}
