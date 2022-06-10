package com.example.final_project_spring.controller;

import com.example.final_project_spring.dto.ResponseApi;
import com.example.final_project_spring.model.Place;
import com.example.final_project_spring.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/place")
@RequiredArgsConstructor
public class PlaceController {
    Logger logger= LoggerFactory.getLogger(PlaceController.class);
    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity getPlaces()
    {
        return ResponseEntity.status(HttpStatus.OK).body(placeService.getPlaces());
    }
    @PostMapping
    public ResponseEntity addPlace(@RequestBody @Valid Place place)
    {
        placeService.addPlace(place);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("Place successfully added",HttpStatus.CREATED.value()));
    }
    @DeleteMapping("remove")
    public ResponseEntity removePlace(@RequestParam Integer place_id)
    {
        placeService.removePlace(place_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseApi("Place deleted successfully",HttpStatus.OK.value()));
    }
}
