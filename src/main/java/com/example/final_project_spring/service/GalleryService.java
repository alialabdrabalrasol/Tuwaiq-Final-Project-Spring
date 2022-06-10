package com.example.final_project_spring.service;

import com.example.final_project_spring.dto.GalleryEventDTO;
import com.example.final_project_spring.dto.GalleryUserDTO;
import com.example.final_project_spring.exception.InvalidIDException;
import com.example.final_project_spring.model.Event;
import com.example.final_project_spring.model.Gallery;
import com.example.final_project_spring.model.Place;
import com.example.final_project_spring.model.User;
import com.example.final_project_spring.repository.EventRepository;
import com.example.final_project_spring.repository.GalleryRepository;
import com.example.final_project_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GalleryService {
    private final GalleryRepository galleryRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    public List<Gallery> getGalleries()
    {
        return galleryRepository.findAll();
    }

    public void addGallery(Gallery gallery,Integer user_id)
    {
        Optional<User> user=userRepository.findById(user_id);
        if(user.isEmpty()){throw new InvalidIDException("Invalid user id");}
        gallery.setUser(user.get());
        galleryRepository.save(gallery);
    }
    public void uploadPhoto(GalleryUserDTO galleryUserDTO){
        Optional<User> user=userRepository.findById(galleryUserDTO.getUser_id());
        if(user.isEmpty()){throw new InvalidIDException("Invalid user id");}
        Gallery user_photo=new Gallery();
        user_photo.setUser(user.get());
        user_photo.setPhoto_url(galleryUserDTO.getPhoto_url());
        user_photo.setPhoto_date(galleryUserDTO.getPic_date().toLocalDate());
        user_photo.setDescription(galleryUserDTO.getDesciption());
        galleryRepository.save(user_photo);
        user.get().getGallery().add(user_photo);
        userRepository.save(user.get());


    }

    public Object addEventtoGallery(GalleryEventDTO galleryEventDTO) {
        Gallery new_gallery=galleryRepository.findById(galleryEventDTO.getGallery_id()).orElseThrow(
                ()->new InvalidIDException("Event id is not valid"));
        Event new_event=eventRepository.findById(galleryEventDTO.getEvent_id()).orElseThrow(
                ()->new InvalidIDException("Event id is not valid"));
       new_gallery.getEvents().add(new_event);
        galleryRepository.save(new_gallery);
        new_event.setGallery(new_gallery);
        eventRepository.save(new_event);
        return galleryRepository.findAll();

    }
}
