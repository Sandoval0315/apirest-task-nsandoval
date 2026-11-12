package com.nsandoval.apiresttask.validation;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(TareaNoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> tareaNoEncontrada(TareaNoEncontradaException error) {
        return respuesta(HttpStatus.NOT_FOUND, error.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validacionInvalida(MethodArgumentNotValidException error) {
        String mensaje = error.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return respuesta(HttpStatus.BAD_REQUEST, mensaje);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> jsonInvalido(HttpMessageNotReadableException error) {
        return respuesta(HttpStatus.BAD_REQUEST, "Json invalido");
    }

    private ResponseEntity<Map<String, Object>> respuesta(HttpStatus estado, String mensaje) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("estado", estado.value());
        respuesta.put("mensaje", mensaje);
        return ResponseEntity.status(estado).body(respuesta);
    }
}
