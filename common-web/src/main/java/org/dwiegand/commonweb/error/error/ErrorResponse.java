package org.dwiegand.commonweb.error.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.dwiegand.commonweb.error.codes.EventCode;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class ErrorResponse {

    int code;

    String description;

    List<EventCode> events;
}
