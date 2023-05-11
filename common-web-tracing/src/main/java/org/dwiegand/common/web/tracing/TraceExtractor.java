package org.dwiegand.common.web.tracing;

import org.dwiegand.common.web.tracing.trace.ParentTrace;
import org.springframework.lang.Nullable;

public interface TraceExtractor <T, V> {

    @Nullable
    ParentTrace<V> extractTrace(T request);
}
