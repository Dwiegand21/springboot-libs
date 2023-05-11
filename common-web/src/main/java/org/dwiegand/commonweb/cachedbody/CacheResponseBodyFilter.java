package org.dwiegand.commonweb.cachedbody;

import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CacheResponseBodyFilter extends OncePerRequestFilter implements OrderedFilter {

    public static final int ORDER = Ordered.LOWEST_PRECEDENCE;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        try {
            filterChain.doFilter(request, responseWrapper);
        } finally {
            CachedBodyUtils.setResponseBody(responseWrapper.getContentAsByteArray());
            responseWrapper.copyBodyToResponse();
        }
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
