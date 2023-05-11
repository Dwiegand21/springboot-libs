package org.dwiegand.common.web.tracing.trace;


public interface CompleteTrace<T> extends ParentTrace<T> {

    T getCurrentId();

    @Override
    T getGlobalId();

    @Override
    T getParentId();
}
