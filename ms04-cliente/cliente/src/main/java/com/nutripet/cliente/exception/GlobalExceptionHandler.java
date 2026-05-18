package com.nutripet.cliente.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
       
    // Maneja los errores de @Valid en los DTOs (ej: olvidaste mandar el teléfono).
 // @ExceptionHandler: Le indica a Spring qué método ejecutar dependiendo de la excepción que "explote".
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>(); // Usamos LinkedHashMap para mantener el orden de los errores.
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errores.put(error.getField(), error.getDefaultMessage()) // Extraemos el "message" definido en el DTO.
        );
        return ResponseEntity.badRequest().body(errores); // Retorna 400 Bad Request.
    }

    // Maneja errores de negocio personalizados (Ej: "Cliente no encontrado con id: 5").
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new LinkedHashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
	

}
