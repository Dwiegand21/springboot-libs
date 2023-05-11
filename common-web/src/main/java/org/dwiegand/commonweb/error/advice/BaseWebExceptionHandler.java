package org.dwiegand.commonweb.error.advice;

import lombok.extern.slf4j.Slf4j;
import org.dwiegand.commonweb.error.codes.EventCodes;
import org.dwiegand.commonweb.error.codes.StatusCodes;
import org.dwiegand.commonweb.error.error.ErrorResponse;
import org.dwiegand.commonweb.error.exceptions.WebException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class BaseWebExceptionHandler {

    @ExceptionHandler(WebException.class)
    protected ResponseEntity<ErrorResponse> handle(WebException e) {
        onWebException(e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .code(StatusCodes.CLIENT_DATA_ERROR.getCode())
                        .description(StatusCodes.CLIENT_DATA_ERROR.getDescription())
                        .events(List.of(EventCodes.VALIDATION_ERROR))
                        .build()
                );
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handle(Throwable e) {
        onUnexpectedException(e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .code(StatusCodes.UNEXPECTED_ERROR.getCode())
                        .description(StatusCodes.UNEXPECTED_ERROR.getDescription())
                        .events(Collections.emptyList())
                        .build()
                );
    }

    protected void onUnexpectedException(Throwable e) { }

    protected void onWebException(WebException e) { }
}
