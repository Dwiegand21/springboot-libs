package org.dwiegand.common.web.tracing;

import org.dwiegand.common.web.tracing.http.TraceInitFilter;
import org.dwiegand.common.web.tracing.http.extractor.DefaultRequestBodyTraceExtractor;
import org.dwiegand.common.web.tracing.http.extractor.HttpServerTraceExtractor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackageClasses = CommonWebTracingProperties.class)
@PropertySource("classpath:common-web-tracing.properties")
public class CommonWebTracingConfiguration {

    @ConditionalOnProperty(
            value = "dwiegand.libs.web.tracing.enabled",
            havingValue = "true"
    )
    @Bean
    TraceInitFilter tracingFilter() {

        return new TraceInitFilter(new DefaultRequestBodyTraceExtractor());
    }

    @ConditionalOnProperty(
            value = "dwiegand.libs.web.tracing.enabled",
            havingValue = "true"
    )
    @ConditionalOnMissingBean(HttpServerTraceExtractor.class)
    @Bean
    HttpServerTraceExtractor defaultHttpServerTraceExtractor() {
        return new DefaultRequestBodyTraceExtractor();
    }

    void foo() {
    }
}
