package com.example.final_project_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class User implements UserDetails{
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
    @Pattern(regexp = "(user|admin)",message = "Type can either be user or visitor")
    private String userType;

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private Set<Place> places;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Gallery> gallery;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Event> events;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.getUserType()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
