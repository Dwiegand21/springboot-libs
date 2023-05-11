package org.dwiegand.commonweb.cachedbody;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;

import java.util.NoSuchElementException;
import java.util.Optional;

@UtilityClass
public class CachedBodyUtils {

    public static final String CACHED_REQUEST_BODY_ATTR = "_CommonWeb.CachedRequestBodyAttr";
    public static final String CACHED_RESPONSE_BODY_ATTR = "_CommonWeb.CachedResponseBodyAttr";

    public static Optional<byte[]> getRequestBody() {

        return Optional.ofNullable((byte[]) getAttributes()
                .getAttribute(CACHED_REQUEST_BODY_ATTR, RequestAttributes.SCOPE_REQUEST)
        );
    }

    public static byte[] getRequestBodyRequired() throws NoSuchElementException {

        return Optional.ofNullable((byte[]) getAttributes()
                        .getAttribute(CACHED_REQUEST_BODY_ATTR, RequestAttributes.SCOPE_REQUEST)
                )
                .orElseThrow();
    }

    public static Optional<byte[]> getResponseBody() {

        return Optional.ofNullable((byte[]) getAttributes()
                .getAttribute(CACHED_RESPONSE_BODY_ATTR, RequestAttributes.SCOPE_REQUEST)
        );
    }

    public static byte[] getResponseBodyRequired() throws NoSuchElementException {

        return Optional.ofNullable((byte[]) getAttributes()
                        .getAttribute(CACHED_RESPONSE_BODY_ATTR, RequestAttributes.SCOPE_REQUEST)
                )
                .orElseThrow();
    }

    static void setRequestBody(byte[] responseBody) {

        getAttributes().setAttribute(CACHED_REQUEST_BODY_ATTR, responseBody, RequestAttributes.SCOPE_REQUEST);
    }

    static void deleteRequestBody() {
        getAttributes().removeAttribute(CACHED_REQUEST_BODY_ATTR, RequestAttributes.SCOPE_REQUEST);
    }

    static void setResponseBody(byte[] responseBody) {
        getAttributes().setAttribute(CACHED_RESPONSE_BODY_ATTR, responseBody, RequestAttributes.SCOPE_REQUEST);
    }

    static void deleteResponseBody() {
        getAttributes().removeAttribute(CACHED_RESPONSE_BODY_ATTR, RequestAttributes.SCOPE_REQUEST);
    }

    private static RequestAttributes getAttributes() {
        return RequestContextHolder.currentRequestAttributes();
    }
}
