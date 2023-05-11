package org.dwiegand.kafka.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = ""
)
public class TestApp {

    public static void main(String[] args) {
        SpringApplication.run(TestApp.class);
    }
}
