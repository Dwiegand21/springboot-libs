package org.dwiegand.commonweb.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.io.Resource;

import java.net.URI;
import java.util.List;
import java.util.Map;


@ConfigurationProperties(prefix = "org.dwiegand.web.client")
@Getter
@Setter
public class CommonWebClientProperties {

    CommonWebClientProperties.Ssl ssl;

    List<CommonWebClientProperties.Client> clients;

    @Getter
    @Setter
    public static class Ssl {

        Boolean enabled;

        Resource keyStoreLocation;

        String keyStorePassword;

        String keyStoreType;

        String keyAlias;

        String keyPassword;

        Resource trustStoreLocation;

        String trustStorePassword;

        String trustStoreType;

        String protocol;
    }

    @Getter
    @Setter
    public static class Client {

        String name;

        URI uri;

        Map<String, String> headers;

        int retries;
    }
}
