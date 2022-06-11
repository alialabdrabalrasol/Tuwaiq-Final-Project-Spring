package com.example.final_project_spring.controller;

import com.example.final_project_spring.dto.GalleryEventDTO;
import com.example.final_project_spring.dto.GalleryUserDTO;
import com.example.final_project_spring.dto.ResponseApi;
import com.example.final_project_spring.model.Gallery;
import com.example.final_project_spring.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/gallery")
@RequiredArgsConstructor
public class GalleryController {
    Logger logger= LoggerFactory.getLogger(GalleryController.class);
    private final GalleryService galleryService;
    @GetMapping
    public ResponseEntity getGalleries()
    {
        logger.info("getGalleries() - GalleryController");
        return ResponseEntity.status(HttpStatus.OK).body(galleryService.getGalleries());
    }
    @PostMapping("{user_id}")
    public ResponseEntity addGallery(@RequestBody @Valid Gallery gallery,@PathVariable Integer user_id)
    {
        logger.info("getGallery() - GalleryController");
        galleryService.addGallery(gallery, user_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("Gallery added",HttpStatus.CREATED.value()));
    }
    @PostMapping("upload-photo")
    public ResponseEntity uploadPhoto(GalleryUserDTO galleryUserDTO)
    {
        logger.info("uploadPhoto() - GalleryController");
        galleryService.uploadPhoto(galleryUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("Photo successfully uploaded",HttpStatus.CREATED.value()));
    }
    @PutMapping()
    public ResponseEntity addEventtoGallery(@RequestBody GalleryEventDTO galleryEventDTO)
    {
        logger.info("addEventtoGallery() - PlaceController");
        return ResponseEntity.status(HttpStatus.OK).body(galleryService.addEventtoGallery(galleryEventDTO));
    }

}
