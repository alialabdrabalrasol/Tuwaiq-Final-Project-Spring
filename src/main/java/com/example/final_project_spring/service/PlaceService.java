package com.example.final_project_spring.service;

import com.example.final_project_spring.exception.InvalidIDException;
import com.example.final_project_spring.exception.InvalidPlaceException;
import com.example.final_project_spring.model.Event;
import com.example.final_project_spring.model.Place;
import com.example.final_project_spring.repository.EventRepository;
import com.example.final_project_spring.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;


    public List<Place> getPlaces()
    {
        return placeRepository.findAll();
    }
    public void addPlace(Place place)
    {
        placeRepository.save(place);
    }


}
