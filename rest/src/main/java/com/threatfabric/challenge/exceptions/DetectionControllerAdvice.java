package com.threatfabric.challenge.exceptions;

import com.threatfabric.challenge.repository.exception.DeviceNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class DetectionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(DeviceNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse deviceNotSupportedExceptionHandler(DeviceNotSupportedException e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

}
