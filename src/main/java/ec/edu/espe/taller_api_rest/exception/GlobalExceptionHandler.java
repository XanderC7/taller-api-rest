package ec.edu.espe.taller_api_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Centraliza el manejo de excepciones para los Controllers
public class GlobalExceptionHandler {

    // Maneja el error 404 - No Encontrado
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarProductoNoEncontrado(ProductoNoEncontradoException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("status", HttpStatus.NOT_FOUND.value()); // Retorna 404
        
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    // Maneja el error 400 - Reglas de negocio incumplidas
    @ExceptionHandler(ProductoInvalidoException.class)
    public ResponseEntity<Map<String, Object>> manejarProductoInvalido(ProductoInvalidoException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("status", HttpStatus.BAD_REQUEST.value()); // Retorna 400
        
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
}
