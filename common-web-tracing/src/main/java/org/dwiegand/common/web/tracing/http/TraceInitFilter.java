package org.dwiegand.common.web.tracing.http;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.dwiegand.common.web.tracing.common.TracingUtils;
import org.dwiegand.common.web.tracing.http.extractor.DefaultRequestBodyTraceExtractor;
import org.dwiegand.commonweb.cachedbody.CacheRequestBodyFilter;
import org.dwiegand.commonweb.cachedbody.CachedBodyUtils;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TraceInitFilter extends OncePerRequestFilter implements OrderedFilter {

    public static final int ORDER = CacheRequestBodyFilter.ORDER + 1;

    DefaultRequestBodyTraceExtractor extractor;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain
    ) throws IOException, ServletException {

        try {

            initTraceContextAndSendRequest(request);

            chain.doFilter(request, response);
        } finally {
            cleanTraceContextAndSendResponse(request, response);
        }
    }

    // request -> filters -> interceptor -> argument resolver -> controller

    private void initTraceContextAndSendRequest(HttpServletRequest request) {

        TracingUtils.setTrace(extractor.extractTrace(request));

        var requestBody = CachedBodyUtils.getRequestBody();
        var trace = TracingUtils.getTrace();

        log.info("request trace: '{}' '{}'", trace, requestBody.map(String::new).orElse("-"));
    }

    private void cleanTraceContextAndSendResponse(HttpServletRequest request, HttpServletResponse response) {

        var trace = TracingUtils.getTrace();
        var responseBody = CachedBodyUtils.getResponseBody();

        log.info("response trace: '{}' '{}'", trace, responseBody.map(String::new).orElse("-"));

        TracingUtils.removeTrace();
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
