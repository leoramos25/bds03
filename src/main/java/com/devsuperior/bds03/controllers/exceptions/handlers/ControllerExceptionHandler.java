package com.devsuperior.bds03.controllers.exceptions.handlers;

import com.devsuperior.bds03.controllers.exceptions.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> argumentValidation(MethodArgumentNotValidException exception, HttpServletRequest request) {
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var error = new ValidationError();
        
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Argument validation exception");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        
        for (FieldError f : exception.getBindingResult().getFieldErrors())
            error.addError(f.getField(), f.getDefaultMessage());
        
        return ResponseEntity.status(status).body(error);
    }
}
