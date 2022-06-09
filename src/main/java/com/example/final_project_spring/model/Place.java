package com.example.final_project_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer place_id;
    @NotEmpty(message = "Country name cannot be empty")
    private String country;
    @NotEmpty(message = "City name cannot be empty")
    private String city;
    @NotEmpty(message = "Place name cannot be empty")
    private String place_name;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Event>events;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User>users;
}
