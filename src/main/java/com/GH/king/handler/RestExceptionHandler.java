package com.GH.king.handler;

import com.GH.king.error.Myerror;
import com.GH.king.expection.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Created by alex on 2016/1/24.
 */

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
        Myerror error = new Myerror();
        error.setTitle("Resource Not Found");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(new Date().getTime());
        error.setDetail(rnfe.getMessage());
        error.setDeveloperMessage(rnfe.getClass().getName()+"  Author: GY");
        return new ResponseEntity<>(error, null, HttpStatus.NOT_FOUND);
    }

}
