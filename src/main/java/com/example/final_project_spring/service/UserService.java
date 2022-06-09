package com.example.final_project_spring.service;

import com.example.final_project_spring.dto.LoginDTO;
import com.example.final_project_spring.dto.UserEventDTO;
import com.example.final_project_spring.dto.UserPlaceDTO;
import com.example.final_project_spring.exception.DuplicateUserException;
import com.example.final_project_spring.exception.InvalidIDException;
import com.example.final_project_spring.exception.InvalidUsernameException;
import com.example.final_project_spring.model.Event;
import com.example.final_project_spring.model.Gallery;
import com.example.final_project_spring.model.Place;
import com.example.final_project_spring.model.User;
import com.example.final_project_spring.repository.EventRepository;
import com.example.final_project_spring.repository.PlaceRepository;
import com.example.final_project_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final EventRepository eventRepository;

    public List<User> getUsers()
    {
        return userRepository.findAll();
    }
    public void addUser(User user)
    {
        Boolean doesUserExist = userRepository.existsUserByUsername(user.getUsername());
        if(doesUserExist){throw new DuplicateUserException("User Already Exists");}

        userRepository.save(user);
    }
//    public User getUser(String username)
//    {
//       Optional<User>user=userRepository.findUserByUsername(username);
//       if(user.isEmpty()){throw new InvalidUsernameException("User not found");}
//        return user.get();
//    }

    public void register(User user) {
        Boolean doesUserExist = userRepository.existsUserByUsername(user.getUsername());
        if(doesUserExist){throw new DuplicateUserException("User Already Exists");}

        userRepository.save(user);
    }
    public void login(LoginDTO login_data){
        String username=login_data.getUsername();
        String password=login_data.getPassword();
       Boolean isUserFound=userRepository.existsUserByUsernameAndPassword(username,password);
       if(!isUserFound){throw new InvalidUsernameException("Invalid username or password");}

    }

    public Object visitPlace(UserPlaceDTO userPlaceDTO) {
        User new_user=userRepository.findById(userPlaceDTO.getUser_id()).orElseThrow(
                ()->new InvalidIDException("Invalid User ID"));
        Place new_place=placeRepository.findById(userPlaceDTO.getPlace_id()).orElseThrow(()->new InvalidIDException("Event id is not valid"));

        new_user.getPlaces().add(new_place);
        userRepository.save(new_user);
        new_place.getUsers().add(new_user);
        placeRepository.save(new_place);
        return userRepository.findAll();
    }
    public Object attendEvent(UserEventDTO userEventDTO)
    {

        User new_user=userRepository.findById(userEventDTO.getUser_id()).orElseThrow(
                ()->new InvalidIDException("User id is not valid"));
        Event new_event=eventRepository.findById(userEventDTO.getEvent_id()).orElseThrow(
                ()->new InvalidIDException("Event id is not valid"));

      

        new_user.getEvents().add(new_event);
        userRepository.save(new_user);
        new_event.setUser(new_user);
        eventRepository.save(new_event);
        return userRepository.findAll();
    }
}
