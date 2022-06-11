package br.com.ernanilima.jcep.resource.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static br.com.ernanilima.jcep.utils.I18n.getMessageByStatusCode;
import static br.com.ernanilima.jcep.utils.Utils.getSimpleErrorMessage;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Erro de validacao do CEP
     * @param e ConstraintViolationException
     * @param r HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest r) {

        StandardError standardError = StandardError.builder()
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .status(UNPROCESSABLE_ENTITY.value())
                .error(getMessageByStatusCode(UNPROCESSABLE_ENTITY.value()))
                .message(getSimpleErrorMessage(e.getMessage()))
                .path(r.getRequestURI())
                .build();

        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(standardError);
    }
}
