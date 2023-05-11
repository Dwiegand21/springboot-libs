package org.dwiegand.common.web.tracing.trace;

import lombok.Value;

import java.util.UUID;


public interface ParentTrace <T> {

    T getGlobalId();

    T getParentId();
}
