package org.dwiegand.common.web.tracing;

import org.dwiegand.common.web.tracing.trace.CompleteTrace;

public interface TraceSetter <T, V> {

    void setTrace(T request, CompleteTrace<V> trace);
}
