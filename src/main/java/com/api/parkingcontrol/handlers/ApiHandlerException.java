package com.api.parkingcontrol.handlers;

import com.api.parkingcontrol.exceptions.ApiException;
import com.api.parkingcontrol.exceptions.ParkingSpotNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiHandlerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errs = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errs.put(field, message);
        });

        return new ResponseEntity<>(errs, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ParkingSpotNotFoundException.class})
    public ResponseEntity<ApiException> handleParkingSpotException(ParkingSpotNotFoundException ex) {

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiEx = ApiException.builder()
                .message(ex.getMessage())
                .throwable(ex.getClass().getSimpleName())
                .httpStatus(notFound)
                .timestamp(ZonedDateTime.now(ZoneId.of("UTC")))
                .build();

        return new ResponseEntity<>(apiEx, notFound);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiException> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiEx = ApiException.builder()
                .message("invalid requested id")
                .throwable(ex.getClass().getSimpleName())
                .httpStatus(badRequest)
                .timestamp(ZonedDateTime.now(ZoneId.of("UTC")))
                .build();

        return new ResponseEntity<>(apiEx, badRequest);
    }
}
