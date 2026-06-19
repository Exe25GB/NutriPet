package com.nutripet.usuario.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String parametro = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(parametro, mensaje);
        });

        return ResponseEntity.badRequest().body(errores);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new LinkedHashMap<>();
        
        error.put("error", ex.getMessage());
        
        return ResponseEntity.badRequest().body(error);
    }
}
