package org.dwiegand.commonweb.error.exceptions;

import lombok.experimental.UtilityClass;
import org.dwiegand.commonweb.error.codes.EventCode;
import org.dwiegand.commonweb.error.codes.StatusCode;
import org.dwiegand.commonweb.error.codes.StatusCodes;
import org.springframework.lang.NonNull;

import java.util.List;

@UtilityClass
public class ApplicationExceptions {

    public static WebException webError(@NonNull StatusCode status) {
        return webError(status, List.of());
    }

    public static WebException webError(@NonNull StatusCode status, @NonNull List<EventCode> events) {
        return webError(status, events, status.getDescription());
    }

    public static WebException webError(@NonNull StatusCode status, @NonNull List<EventCode> events, Throwable cause) {
        return new WebException(status, events, status.getDescription(), cause);
    }

    public static WebException webError(@NonNull StatusCode status, @NonNull List<EventCode> events, String message) {
        return new WebException(status, events, message, null);
    }

    public static WebException webError(@NonNull StatusCode status, @NonNull List<EventCode> events, String message, Throwable cause) {
        return new WebException(status, events, message, cause);
    }

    public static WebException unexpected() {
        return webError(StatusCodes.UNEXPECTED_ERROR, List.of(), "", null);
    }

    public static WebException unexpected(Throwable cause) {
        return webError(StatusCodes.UNEXPECTED_ERROR, List.of(), "", cause);
    }

    public static WebException unexpected(String message, Throwable cause) {
        return webError(StatusCodes.UNEXPECTED_ERROR, List.of(), message, cause);
    }
}
