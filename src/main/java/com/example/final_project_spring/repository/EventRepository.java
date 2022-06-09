package com.example.final_project_spring.repository;

import com.example.final_project_spring.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    @Query(value = "SELECT t from Event t")
    Object SortByDate();
}
