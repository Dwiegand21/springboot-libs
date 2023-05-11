package org.dwiegand.context;


public interface ContextResourceAdapter {

    String getScoringId();

    void setScoringId(String value);

    String getRequestId();

    void setRequestId(String value);

    void setAdditional(String key, Object value);

    Object getAdditional(String key);

    void bindResource(ContextResource value);

    ContextResource unbindResource();

    void clear();
}
