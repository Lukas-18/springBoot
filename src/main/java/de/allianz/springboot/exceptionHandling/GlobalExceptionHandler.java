package de.allianz.springboot.exceptionHandling;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(RuntimeException ex) {
        return new ResponseEntity<>(ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    */

    private  final MessageSource messageSource;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundException(RuntimeException exception, WebRequest request) {
        //System.out.println("ENTITY NOT FOUND!");
        //return null;
        String message = messageSource.getMessage("exception.idNotFound", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
