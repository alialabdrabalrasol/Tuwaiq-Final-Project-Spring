package com.example.final_project_spring.controller;

import com.example.final_project_spring.dto.EventDTO;
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
    public ResponseEntity addEvent(@RequestBody @Valid Event event)
    {
        eventService.addEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("Event successfully added",HttpStatus.CREATED.value()));
    }
    @PutMapping()
    public ResponseEntity addPlacetoEvent(@RequestBody EventDTO eventDTO)
    {
                return ResponseEntity.status(HttpStatus.OK).body( eventService.addPlacetoEvent(eventDTO));
    }
    @DeleteMapping()
    public ResponseEntity deleteEvent()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseApi("Event successfully deleted",HttpStatus.OK.value()));
    }

}
