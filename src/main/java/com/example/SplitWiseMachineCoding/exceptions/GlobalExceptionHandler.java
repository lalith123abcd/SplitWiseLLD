package com.example.SplitWiseMachineCoding.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)

              .body(exception.getMessage());
    }
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<String> handleGroupNotFound(GroupNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<String> handleDuplicateMember(DuplicateMemberException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserNotInGroupException.class)
    public ResponseEntity<String> handleUserNotInGroup(UserNotInGroupException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidExpenseException.class)
    public ResponseEntity<String> handleUserNotFound(InvalidExpenseException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)

                .body(exception.getMessage());
    }
}
