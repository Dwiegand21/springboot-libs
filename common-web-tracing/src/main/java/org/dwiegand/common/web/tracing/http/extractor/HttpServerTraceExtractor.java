package org.dwiegand.common.web.tracing.http.extractor;

import org.dwiegand.common.web.tracing.TraceExtractor;

import javax.servlet.http.HttpServletRequest;

public interface HttpServerTraceExtractor extends TraceExtractor<HttpServletRequest, String> {
}
