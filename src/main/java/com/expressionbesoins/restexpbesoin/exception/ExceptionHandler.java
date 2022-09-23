package com.expressionbesoins.restexpbesoin.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ConfirmPassowrdIncorrect.class})
    ResponseEntity<Object> confirmPasswordIncorrect(ConfirmPassowrdIncorrect confirmPassowrdIncorrect){
        ErrorMessage message = ErrorMessage.builder()
                .message(confirmPassowrdIncorrect.getMessage())
                .status(412)
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserHasNotAuthorisation.class})
    ResponseEntity<Object> userHasNotAuthority(UserHasNotAuthorisation userHasNotAuthorisation){
        ErrorMessage message = ErrorMessage.builder()
                .message(userHasNotAuthorisation.getMessage())
                .status(402)
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
