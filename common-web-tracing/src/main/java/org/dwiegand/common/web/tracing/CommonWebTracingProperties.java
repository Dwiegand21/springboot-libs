package org.dwiegand.common.web.tracing;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@ConfigurationProperties(prefix = "dwiegand.libs.web.tracing")
@Validated
@Getter
@Setter
public class CommonWebTracingProperties {

    @NotNull
    Boolean enabled;
}
