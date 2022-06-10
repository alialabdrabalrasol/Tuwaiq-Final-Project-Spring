package com.example.final_project_spring.controller;

import com.example.final_project_spring.dto.EventDTO;
import com.example.final_project_spring.dto.EventPlaceDTO;
import com.example.final_project_spring.dto.ResponseApi;
import com.example.final_project_spring.model.Event;
import com.example.final_project_spring.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity getEvents()
    {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEvents());
    }
    @PostMapping()
    public ResponseEntity addEvent(@RequestBody EventDTO eventDTO)
    {
        eventService.addEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("Event successfully added",HttpStatus.CREATED.value()));
    }
    @PutMapping()
    public ResponseEntity addPlacetoEvent(@RequestBody EventPlaceDTO eventPlaceDTO)
    {
                return ResponseEntity.status(HttpStatus.OK).body( eventService.addPlacetoEvent(eventPlaceDTO));
    }
    @GetMapping("{user_id}")
    public ResponseEntity getUserEvents(@PathVariable Integer user_id){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getUserEvents(user_id));
    }


}
