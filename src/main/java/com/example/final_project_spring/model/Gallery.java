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
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gallery_id;
    @NotEmpty(message = "Photo is required")
    private String photo_url;
    @NotEmpty(message = "Description is required")
    private String description;
    @NotNull(message = "Photo date is required")
    private LocalDate photo_date;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "gallery",cascade = CascadeType.ALL)
    private Set<Event>events;

}
