package org.dwiegand.app;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TestService {

    @Timed("test.service")
    public String test(String message) {

        return ">> " + message;
    }
}
