package org.dwiegand.common.web.tracing.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dwiegand.common.web.tracing.trace.ParentTrace;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TracingUtils {

    public static final String TRACE_OBJECT_KEY = "_RequestTraceObject";

    public static Optional<ParentTrace> getTrace() {

        try {
            return Optional.ofNullable((ParentTrace) RequestContextHolder.currentRequestAttributes().getAttribute(TRACE_OBJECT_KEY, RequestAttributes.SCOPE_REQUEST));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static ParentTrace getTraceRequired() {

        return (ParentTrace) RequestContextHolder.currentRequestAttributes()
                .getAttribute(TRACE_OBJECT_KEY, RequestAttributes.SCOPE_REQUEST);
    }

    public static void setTrace(ParentTrace trace) {
        RequestContextHolder.currentRequestAttributes().setAttribute(TRACE_OBJECT_KEY, trace, RequestAttributes.SCOPE_REQUEST);
    }

    public static void removeTrace() {

        RequestContextHolder.currentRequestAttributes().removeAttribute(TRACE_OBJECT_KEY, RequestAttributes.SCOPE_REQUEST);
    }
}
