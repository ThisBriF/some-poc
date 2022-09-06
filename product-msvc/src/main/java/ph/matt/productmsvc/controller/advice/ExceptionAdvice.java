package ph.matt.productmsvc.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ph.matt.productmsvc.exception.ErrorResponse;
import ph.matt.productmsvc.exception.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException cause) {
        return new ResponseEntity<>(
                new ErrorResponse(cause.getMessage(), "ResourceNotFound"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException cause) {
        return new ResponseEntity<>(
                new ErrorResponse(cause.getMessage(), "RuntimeException"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
