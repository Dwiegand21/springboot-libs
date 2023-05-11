package org.dwiegand.common.web.tracing.kafka;


import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.dwiegand.common.web.tracing.common.TracingUtils;

import java.util.Map;


public class KafkaTraceProducerInterceptor <K, V> implements ProducerInterceptor<K, V> {

    @Override
    public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record) {

        // add trace in record

        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        // ...
    }

    @Override
    public void close() {
        // ...
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // ...
    }
}
