package com.example.final_project_spring.advice;

import com.example.final_project_spring.dto.ResponseApi;
import com.example.final_project_spring.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValid(MethodArgumentNotValidException e)
    {
        String err=e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi(err,HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrity(DataIntegrityViolationException dataIntegrityViolationException){
        String err=dataIntegrityViolationException.getRootCause().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi(err,400));
    }
    @ExceptionHandler(InvalidIDException.class)
    public ResponseEntity InvalidID(InvalidIDException e)
    {
        String err=e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi(err,HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity DuplicatedUser(DuplicateUserException e)
    {
        String err=e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi(err,HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity InvalidUsername(InvalidUsernameException e)
    {
        String err=e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi(err,HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(InvalidPlaceException.class)
    public ResponseEntity InvalidPlace(InvalidPlaceException e)
    {
        String err=e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi(err,HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(DuplicateItemException.class)
    public ResponseEntity DuplicateItem(DuplicateItemException e)
    {
        String err=e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi(err,HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity InvalidDate(InvalidDateException e)
    {
        String err=e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi(err,HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e){
      String err=e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi("SERVER ERROR !",500));
    }
}
