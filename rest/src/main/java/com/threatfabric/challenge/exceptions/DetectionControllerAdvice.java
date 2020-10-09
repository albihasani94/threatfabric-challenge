package com.threatfabric.challenge.exceptions;

import com.threatfabric.challenge.repository.exception.DeviceNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.Collectors;

@ControllerAdvice
public class DetectionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(DeviceNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse deviceNotSupportedExceptionHandler(DeviceNotSupportedException e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse fieldInvalidExceptionHandler(MethodArgumentNotValidException e) {
        var violations = e.getBindingResult().getFieldErrors().stream()
                .map(toViolation())
                .collect(Collectors.toList());

        return new ValidationErrorResponse(violations);
    }

    private Function<FieldError, Violation> toViolation() {
        return error -> new Violation(error.getField(), error.getDefaultMessage());
    }

}
