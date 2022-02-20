package com.api.parkingcontrol.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@Builder
public class ApiException {
    private final String message;
    private final String throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
