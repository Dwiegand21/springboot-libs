package org.dwiegand.common.web.tracing.kafka;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.dwiegand.common.web.tracing.common.TracingUtils;
import org.dwiegand.common.web.tracing.trace.ParentTrace;
import org.dwiegand.common.web.tracing.kafka.extractor.KafkaTraceExtractor;
import org.springframework.kafka.listener.ConsumerAwareRecordInterceptor;
import org.springframework.lang.Nullable;


@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaConsumerTraceConsumerInterceptor <K, T> implements ConsumerAwareRecordInterceptor<K, T> {

    KafkaTraceExtractor traceExtractor;

    @Nullable
    @Override
    public ConsumerRecord<K, T> intercept(ConsumerRecord<K, T> record, Consumer<K, T> consumer) {

        ParentTrace trace = traceExtractor.extractTrace(record);
        TracingUtils.setTrace(trace);

        return record;
    }

    @Override
    public void afterRecord(ConsumerRecord<K, T> record, Consumer<K, T> consumer) {

        TracingUtils.removeTrace();

        ConsumerAwareRecordInterceptor.super.afterRecord(record, consumer);
    }
}
