package com.example.final_project_spring.exception;

public class InvalidPlaceException extends RuntimeException{
    public InvalidPlaceException(String message) {
        super(message);
    }
}
