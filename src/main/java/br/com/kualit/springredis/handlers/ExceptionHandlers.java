package br.com.kualit.springredis.handlers;

import br.com.kualit.springredis.model.record.ApplicationError;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class ExceptionHandlers {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApplicationError> handleRuntTimeException(RuntimeException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(new ApplicationError(ex.getMessage()));
    }
}
