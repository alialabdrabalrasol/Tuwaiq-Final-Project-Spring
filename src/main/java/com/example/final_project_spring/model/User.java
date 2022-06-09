package com.example.final_project_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//this hides the password when a get method is called
    private String password;
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "User type cannot be empty")
    @Pattern(regexp = "(user|visitor)",message = "Type can either be user or visitor")
    private String userType;

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private Set<Place> places;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Gallery> gallery;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Event> events;


}
