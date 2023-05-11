package org.dwiegand.kafka.core;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.listener.ContainerProperties;


@Configuration
public class KafkaCoreConfiguration {

    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        return factory;
    }

    public ContainerProperties containerProperties() {
        var properties = new ContainerProperties("");
        KafkaProperties kafkaProperties;
        MethodKafkaListenerEndpoint endpoint;

        return new ContainerProperties("");
    }
}
