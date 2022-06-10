package com.example.final_project_spring.repository;

import com.example.final_project_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
Boolean existsUserByUsername(String username);
Boolean existsUserByUsernameAndPassword(String username,String password);

    Optional<User> findUserByUsername(String username);
}

