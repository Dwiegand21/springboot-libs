package org.dwiegand.commonweb.client;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.net.ssl.SSLContext;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = CommonWebClientProperties.class)
public class ClientConfiguration {

    CommonWebClientProperties properties;

    @ConditionalOnProperty(value = "org.dwiegand.web.client.ssl.enabled", havingValue = "true")
    @Bean
    RestTemplateCustomizer restTemplateCustomizer() {

        var requestFactory = createRequestFactoryWithSsl();

        return restTemplate -> restTemplate.setRequestFactory(requestFactory);
    }

    private ClientHttpRequestFactory createRequestFactoryWithSsl() {

        var httpClient = createHttpClientWithSsl();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        return requestFactory;
    }

    private HttpClient createHttpClientWithSsl() {

        var sslContext = createSslContext();

        return HttpClients.custom()
                .setSSLContext(sslContext)
                .build();
    }

    private SSLContext createSslContext() {

        try {
            var ssl = properties.getSsl();

            return SSLContextBuilder.create()
                    .setProtocol(ssl.getProtocol())
                    .loadKeyMaterial(
                            ssl.getKeyStoreLocation().getFile(),
                            ssl.getKeyStorePassword().toCharArray(),
                            ssl.getKeyPassword().toCharArray(),
                            (aliases, socket) -> ssl.getKeyAlias()
                    )
                    .loadTrustMaterial(
                            ssl.getTrustStoreLocation().getFile(),
                            ssl.getTrustStorePassword().toCharArray()
                    )
                    .build();
        } catch (Exception e) {
            throw new BeanCreationException("sslContext", "Не удалось инициализировать ssl context", e);
        }
    }
}
