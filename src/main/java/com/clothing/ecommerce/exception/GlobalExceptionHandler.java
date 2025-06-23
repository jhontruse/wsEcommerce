package com.clothing.ecommerce.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.clothing.ecommerce.exception.capas.ControllerException;
import com.clothing.ecommerce.exception.capas.DuplicateKeyException;
import com.clothing.ecommerce.exception.capas.NotDataFoundException;
import com.clothing.ecommerce.exception.capas.RepositoryException;
import com.clothing.ecommerce.exception.capas.ServiceException;
import com.clothing.ecommerce.exception.capas.ValidationException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppException(AppException ex, HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof ControllerException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof RepositoryException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof ServiceException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof ValidationException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof NotDataFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof DuplicateKeyException) {
            status = HttpStatus.CONFLICT;
        }

        error.put("error", ex.getMessage());
        error.put("layer", ex.getLayer());
        error.put("timestamp", Instant.now());
        error.put("exception", ex.getClass().getSimpleName());
        error.put("status", status.value());
        error.put("statustext", status.getReasonPhrase());
        error.put("path", request.getRequestURI());
        error.put("method", request.getMethod());
        error.put("developerMessage", ex.getCause() != null ? ex.getCause().getMessage() : null);
        return ResponseEntity.status(status).body(error);
    }

    /*
     * @ExceptionHandler(ValidationException.class)
     * public ResponseEntity<Map<String, Object>>
     * handleValidationException(ValidationException ex,
     * HttpServletRequest request) {
     * Map<String, Object> error = new HashMap<>();
     * HttpStatus status = HttpStatus.EXPECTATION_FAILED;
     * 
     * error.put("error", ex.getMessage());
     * error.put("layer", ex.getLayer());
     * error.put("timestamp", Instant.now());
     * error.put("exception", ex.getClass().getSimpleName());
     * error.put("status", status.value());
     * error.put("statustext", status.getReasonPhrase());
     * error.put("path", request.getRequestURI());
     * error.put("method", request.getMethod());
     * error.put("developerMessage", ex.getCause() != null ?
     * ex.getCause().getMessage() : null);
     * return ResponseEntity.status(status).body(error);
     * }
     */

}
