package org.dwiegand.commonweb.support;

import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.web.filter.OncePerRequestFilter;


public abstract class OrderedAppFilter extends OncePerRequestFilter implements OrderedFilter {

    @Override
    public int getOrder() {
        return 0;
    }
}
