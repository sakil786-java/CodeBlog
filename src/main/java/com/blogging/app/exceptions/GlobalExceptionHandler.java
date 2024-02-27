package com.blogging.app.exceptions;

import com.blogging.app.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>resourceNotFoundExceptionHandler(ResourceNotFoundException exception)
    {
        String message=exception.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);
        return  new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND) ;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String ,String>>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String,String> response=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
      String fieldName=((FieldError)error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
    }



}
