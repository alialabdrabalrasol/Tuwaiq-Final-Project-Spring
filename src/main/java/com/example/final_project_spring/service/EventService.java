package com.example.final_project_spring.service;

import com.example.final_project_spring.dto.EventDTO;
import com.example.final_project_spring.exception.DuplicateItemException;
import com.example.final_project_spring.exception.InvalidDateException;
import com.example.final_project_spring.exception.InvalidIDException;
import com.example.final_project_spring.exception.InvalidPlaceException;
import com.example.final_project_spring.model.Event;
import com.example.final_project_spring.model.Place;
import com.example.final_project_spring.repository.EventRepository;
import com.example.final_project_spring.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;


    public List<Event> getEvents()
    {
        return eventRepository.findAll();
    }

    public void addEvent(Event event)
    {
        LocalDate now=LocalDate.now();
        if(event.getEvent_date().isBefore(now)){
            throw new InvalidDateException("Event date has to be after "+now);
        }

        eventRepository.save(event);



    }

    public Object addPlacetoEvent(EventDTO eventDTO) {
        Event new_event=eventRepository.findById(eventDTO.getEvent_id()).orElseThrow(
                ()->new InvalidIDException("Event id is not valid"));
        Place new_place=placeRepository.findById(eventDTO.getPlace_id()).orElseThrow(()->new InvalidIDException("Event id is not valid"));

        new_event.getPlaces().add(new_place);
        eventRepository.save(new_event);
        new_place.getEvents().add(new_event);
        placeRepository.save(new_place);
        return eventRepository.findAll();

    }
}
