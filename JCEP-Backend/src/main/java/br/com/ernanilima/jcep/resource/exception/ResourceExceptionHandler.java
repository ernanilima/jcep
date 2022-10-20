package br.com.ernanilima.jcep.resource.exception;

import br.com.ernanilima.jcep.service.exception.RegionNotFoundException;
import br.com.ernanilima.jcep.service.exception.StateNotFoundException;
import br.com.ernanilima.jcep.service.exception.ZipCodeNotFoundException;
import br.com.ernanilima.jcep.service.validation.RequiredCountryAndRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static br.com.ernanilima.jcep.utils.I18n.*;
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

    /**
     * Erro de validacao
     * Atualmente usado para classes com a anotacao {@link RequiredCountryAndRegion}
     * @param e BindException
     * @param r HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<StandardError> constraintViolation(BindException e, HttpServletRequest r) {
        String message = e.getBindingResult().getGlobalErrors().get(0).getDefaultMessage();

        StandardError standardError = StandardError.builder()
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .status(UNPROCESSABLE_ENTITY.value())
                .error(getMessageByStatusCode(UNPROCESSABLE_ENTITY.value()))
                .message(message)
                .path(r.getRequestURI())
                .build();

        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(standardError);
    }

    /**
     * CEP nao encontrado
     * @param e ZipCodeNotFoundException
     * @param r HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @ExceptionHandler(ZipCodeNotFoundException.class)
    public ResponseEntity<StandardError> zipCodeNotFound(ZipCodeNotFoundException e, HttpServletRequest r) {

        StandardError standardError = StandardError.builder()
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .status(NOT_FOUND.value())
                .error(getMessageByStatusCode(NOT_FOUND.value()))
                .message(e.getMessage())
                .path(r.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    /**
     * Endpoint nao encontrado
     * @param e NoHandlerFoundException
     * @param r HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<StandardError> noHandlerFound(NoHandlerFoundException e, HttpServletRequest r) {

        String message = MessageFormat.format(getMessage(ENDPOINT_NOT_FOUND), e.getHttpMethod(), e.getRequestURL());
        StandardError standardError = StandardError.builder()
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .status(NOT_FOUND.value())
                .error(getMessageByStatusCode(NOT_FOUND.value()))
                .message(message)
                .path(r.getRequestURI())
                .build();

        return ResponseEntity.status(NOT_FOUND).body(standardError);
    }

    /**
     * Quando o metodo recebido nao for suportado, exemplo: recebido DELETE quando deveria ser GET
     * @param e HttpRequestMethodNotSupportedException
     * @param r HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> methodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest r) {

        String message = MessageFormat.format(getMessage(METHOD_NOT_SUPPORTED), e.getMethod());
        StandardError standardError = StandardError.builder()
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .status(BAD_REQUEST.value())
                .error(getMessageByStatusCode(BAD_REQUEST.value()))
                .message(message)
                .path(r.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    /**
     * Regiao nao encontrada
     * @param e RegionNotFoundException
     * @param r HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @ExceptionHandler(RegionNotFoundException.class)
    public ResponseEntity<StandardError> regionNotFound(RegionNotFoundException e, HttpServletRequest r) {

        StandardError standardError = StandardError.builder()
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .status(NOT_FOUND.value())
                .error(getMessageByStatusCode(NOT_FOUND.value()))
                .message(e.getMessage())
                .path(r.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    /**
     * Estado nao encontrado
     * @param e StateNotFoundException
     * @param r HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @ExceptionHandler(StateNotFoundException.class)
    public ResponseEntity<StandardError> stateNotFound(StateNotFoundException e, HttpServletRequest r) {

        StandardError standardError = StandardError.builder()
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .status(NOT_FOUND.value())
                .error(getMessageByStatusCode(NOT_FOUND.value()))
                .message(e.getMessage())
                .path(r.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }
}
