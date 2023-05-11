package org.dwiegand.context;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface ContextResource {

    @Nullable
    String getScoringId();

    void setScoringId(@Nullable String value);

    @Nullable
    String getRequestId();

    void setRequestId(@Nullable String value);

    void setAdditional(@NonNull String key, @Nullable Object value);

    @Nullable
    Object getAdditional(@NonNull String key);

    void clear();
}
