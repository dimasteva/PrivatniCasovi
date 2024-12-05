package com.jahaci.edukacija.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidLessonTimeException.class)
    public ResponseEntity<String> handleInvalidLessonTimeException(InvalidLessonTimeException invalidLessonTimeException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(invalidLessonTimeException.getMessage());
    }

    @ExceptionHandler(InvalidLoginDataException.class)
    public ResponseEntity<String> handleInvalidLoginDataException(InvalidLoginDataException invalidLessonTimeException) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(invalidLessonTimeException.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException usernameAlreadyExistsException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(usernameAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(UsernameMustContainSpaceException.class)
    public ResponseEntity<String> handleUsernameMustContainSpaceException(UsernameMustContainSpaceException usernameMustContainSpaceException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(usernameMustContainSpaceException.getMessage());
    }


}
