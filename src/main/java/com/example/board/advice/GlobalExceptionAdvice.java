package com.example.board.advice;

import com.example.board.exception.BusinessLogicException;
import com.example.board.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)//HTTP Response에 HTTP Status를 포함하여 보내는 어노테이션
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());

        return response;

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleContraintViolationException(ConstraintViolationException e){
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());

        return response;
    }

    @ExceptionHandler
    public ResponseEntity handleResourceNotFoundException(BusinessLogicException e){
        System.out.println(e.getExceptionCode().getStatus());
        System.out.println(e.getMessage());

        return new ResponseEntity<>(HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }

}
