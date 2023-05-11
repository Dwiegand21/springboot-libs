package org.dwiegand.commonweb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringBootApplication(scanBasePackageClasses = CommonWebConfiguration.class)
class CommonWebConfigurationTest {

}