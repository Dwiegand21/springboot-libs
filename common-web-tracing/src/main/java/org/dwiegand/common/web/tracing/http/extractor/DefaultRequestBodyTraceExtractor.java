package org.dwiegand.common.web.tracing.http.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dwiegand.common.web.tracing.trace.ParentTrace;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.function.Supplier;


public class DefaultRequestBodyTraceExtractor implements HttpServerTraceExtractor {

    public @Nullable
    ParentTrace extractTrace(HttpServletRequest servletRequest) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            var node = mapper.readValue(servletRequest.getInputStream(), JsonNode.class);

            var globalId = tryDo(() -> UUID.fromString(node.path("system").path("globalId").asText()), UUID.randomUUID());
            var parentId = tryDo(() -> UUID.fromString(node.path("system").path("spanId").asText()), UUID.randomUUID());
            var childId = UUID.randomUUID();

            return new ParentTrace(globalId, parentId, childId);
        } catch (Exception e) {
            return null;
        }
    }

    private <T> T tryDo(Supplier<T> f, T defaultValue) {
        try {
            return f.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
