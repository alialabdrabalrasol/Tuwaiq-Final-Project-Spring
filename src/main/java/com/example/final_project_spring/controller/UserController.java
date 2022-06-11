package com.example.final_project_spring.controller;

import com.example.final_project_spring.dto.LoginDTO;
import com.example.final_project_spring.dto.ResponseApi;
import com.example.final_project_spring.dto.UserEventDTO;
import com.example.final_project_spring.dto.UserPlaceDTO;
import com.example.final_project_spring.model.User;
import com.example.final_project_spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping
    public ResponseEntity getUsers()
    {
        logger.info("getUsers() - User controller");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid User user)
    {
        logger.info("addUsers() - User controller");
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("User successfully added",HttpStatus.CREATED.value()));
    }
    @PostMapping("register")
    public ResponseEntity register(@RequestBody @Valid User user){
        logger.info("register - User controller");
        userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi("User successfully registered",HttpStatus.CREATED.value()));
    }
    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginDTO login_data)
    {
        logger.info("login() - User controller");
        userService.login(login_data);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseApi("Welcome "+login_data.getUsername(),HttpStatus.OK.value()));
    }
    @PostMapping("log-out")
    public ResponseEntity logOut()
    {
        logger.info("logOut() - User controller");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseApi("Logged out",HttpStatus.OK.value()));
    }

    @PutMapping("visit")
    public ResponseEntity visitPlace(@RequestBody UserPlaceDTO userPlaceDTO)
    {
        logger.info("visitPlace() - User controller");
        return ResponseEntity.status(HttpStatus.OK).body(userService.visitPlace(userPlaceDTO));
    }

    @PutMapping("attend")
    public ResponseEntity attendEvent(@RequestBody UserEventDTO userEventDTO)
    {
        logger.info("attendEvent() - User controller");
        return ResponseEntity.status(HttpStatus.OK).body(userService.attendEvent(userEventDTO));
    }
    @DeleteMapping("remove-event")
    public ResponseEntity removeEventfromUser(UserEventDTO userEventDTO)
    {
        logger.info("removeEventfromUser() - User controller");
        userService.deleteEventfromUser(userEventDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseApi("Event deleted from user",HttpStatus.OK.value()));

    }

}
