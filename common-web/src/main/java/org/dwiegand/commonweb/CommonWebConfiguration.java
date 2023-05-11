package org.dwiegand.commonweb;

import org.dwiegand.commonweb.cachedbody.CacheRequestBodyFilter;
import org.dwiegand.commonweb.cachedbody.CacheResponseBodyFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackageClasses = CommonWebProperties.class)
public class CommonWebConfiguration {

    @ConditionalOnProperty(value = "dwiegand.libs.web.cache-response", havingValue = "true")
    @Bean
    CacheResponseBodyFilter cacheResponseBodyFilter() {
        return new CacheResponseBodyFilter();
    }

    @ConditionalOnProperty(value = "dwiegand.libs.web.cache-request", havingValue = "true")
    @Bean
    CacheRequestBodyFilter cacheRequestBodyFilter() {
        return new CacheRequestBodyFilter();
    }

}
