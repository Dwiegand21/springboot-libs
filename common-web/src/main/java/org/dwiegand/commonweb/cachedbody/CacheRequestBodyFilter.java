package org.dwiegand.commonweb.cachedbody;

import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.dwiegand.commonweb.cachedbody.common.CachedBodyRequestWrapper;

public class CacheRequestBodyFilter extends OncePerRequestFilter implements OrderedFilter {

    public static final int ORDER = OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER + 1;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            CachedBodyRequestWrapper requestWrapper = new CachedBodyRequestWrapper(request);

            CachedBodyUtils.setRequestBody(requestWrapper.getInputStream().readAllBytes());

            filterChain.doFilter(requestWrapper, response);
        } finally {
            CachedBodyUtils.deleteRequestBody();
        }
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
