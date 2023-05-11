package org.dwiegand.common.metrics;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonMetricsConfiguration {

    @ConditionalOnProperty(
            value = "dwiegand.libs.common.metrics.custom-metrics-aspect-enabled",
            havingValue = "true"
    )
    @ConditionalOnMissingBean(CountedAspect.class)
    @Bean
    public CountedAspect defaultCountedAspect(MeterRegistry registry) {
        return new CountedAspect(registry);
    }

    @ConditionalOnProperty(
            value = "dwiegand.libs.common.metrics.custom-metrics-aspect-enabled",
            havingValue = "true"
    )
    @ConditionalOnMissingBean(TimedAspect.class)
    @Bean
    public TimedAspect defaultTimedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
