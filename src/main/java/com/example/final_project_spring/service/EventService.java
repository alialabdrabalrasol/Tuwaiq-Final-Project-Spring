package com.example.final_project_spring.service;

import com.example.final_project_spring.dto.EventDTO;
import com.example.final_project_spring.dto.EventPlaceDTO;
import com.example.final_project_spring.exception.InvalidDateException;
import com.example.final_project_spring.exception.InvalidIDException;
import com.example.final_project_spring.model.Event;
import com.example.final_project_spring.model.Place;
import com.example.final_project_spring.repository.EventRepository;
import com.example.final_project_spring.repository.PlaceRepository;
import com.example.final_project_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;


    public List<Event> getEvents()
    {
        return eventRepository.findAll();
    }

    public void addEvent(EventDTO eventDTO)
    {
        Event event=new Event();
        String event_date_time=eventDTO.getStarting_date()+"T"+eventDTO.getStarting_time();
        event.setDescription(eventDTO.getDescription());
        event.setEvent_date(LocalDateTime.parse(event_date_time));

        if(event.getEvent_date().isBefore(LocalDateTime.now())){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formatDateTime = LocalDateTime.now().format(format);
            throw new InvalidDateException("Event has to be after "+formatDateTime);
        }


        eventRepository.save(event);



    }
    public Set<Event> getUserEvents(Integer user_id){
        return userRepository.findById(user_id).get().getEvents();
    }
//    public Object getFirstEvent(Integer user_id){
//       List<Event>events=new ArrayList<>(userRepository.findById(user_id).get().getEvents());
//       Collections.sort(events, Comparator.comparing(Event::getEvent_date));
//       Collections.sort(events, Comparator.comparing(Event::getEvent_time));
//
//        return events;
//    }

    public Object addPlacetoEvent(EventPlaceDTO eventPlaceDTO) {
        Event new_event=eventRepository.findById(eventPlaceDTO.getEvent_id()).orElseThrow(
                ()->new InvalidIDException("Event id is not valid"));
        Place new_place=placeRepository.findById(eventPlaceDTO.getPlace_id()).orElseThrow(()->new InvalidIDException("Event id is not valid"));

        new_event.getPlaces().add(new_place);
        eventRepository.save(new_event);
        new_place.getEvents().add(new_event);
        placeRepository.save(new_place);
        return eventRepository.findAll();

    }

}
