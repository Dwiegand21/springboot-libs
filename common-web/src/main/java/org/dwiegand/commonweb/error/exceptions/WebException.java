package org.dwiegand.commonweb.error.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.dwiegand.commonweb.error.codes.EventCode;
import org.dwiegand.commonweb.error.codes.StatusCode;

import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebException extends RuntimeException {

    int code;

    String description;

    List<EventCode> events;

    WebException(StatusCode status, List<EventCode> events, String message, Throwable cause) {
        super(message, cause);
        this.code = status.getCode();
        this.description = status.getDescription();
        this.events = new ArrayList<>(events);
    }
}
