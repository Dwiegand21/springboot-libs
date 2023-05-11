package org.dwiegand.common.web.tracing.kafka.extractor;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.dwiegand.common.web.tracing.trace.ParentTrace;
import org.dwiegand.common.web.tracing.TraceExtractor;
import org.springframework.lang.Nullable;

import java.util.UUID;

public class KafkaTraceExtractor implements TraceExtractor<ConsumerRecord<?, ?>> {

    @Nullable
    @Override
    public ParentTrace extractTrace(ConsumerRecord<?, ?> request) {

        // logic for extract trace from headers

        return new ParentTrace(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }
}
