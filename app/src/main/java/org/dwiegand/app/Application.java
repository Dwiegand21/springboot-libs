package org.dwiegand.app;

import org.dwiegand.common.web.tracing.CommonWebTracingConfiguration;
import org.dwiegand.commonweb.CommonWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CommonWebConfiguration.class, CommonWebTracingConfiguration.class, CommonWebConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
