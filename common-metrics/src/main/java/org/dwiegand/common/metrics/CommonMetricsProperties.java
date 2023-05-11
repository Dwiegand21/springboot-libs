package org.dwiegand.common.metrics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dwiegand.libs.common.metrics")
@Getter
@Setter
public class CommonMetricsProperties {

    Boolean customMetricsAspectEnabled;
}
