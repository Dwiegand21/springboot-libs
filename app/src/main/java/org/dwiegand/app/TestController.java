package org.dwiegand.app;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.dwiegand.commonweb.error.codes.EventCodes;
import org.dwiegand.commonweb.error.codes.StatusCodes;
import org.dwiegand.commonweb.error.exceptions.ApplicationExceptions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@AllArgsConstructor
public class TestController {

    TestService service;

    @PostMapping("/test")
    public Response foo(@RequestBody Request request) {

        if (request != null) {
            throw ApplicationExceptions.webError(StatusCodes.CLIENT_DATA_ERROR, List.of(EventCodes.VALIDATION_ERROR));
        }

        return new Response(service.test(request.getMessage()));
    }

    @Value
    @NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
    @AllArgsConstructor
    public static class Request {
        String message;
    }

    @Value
    public static class Response {

        String label;
    }
}
