package com.example.final_project_spring.exception;

public class DuplicateItemException extends RuntimeException{
    public DuplicateItemException(String message) {
        super(message);
    }
}
