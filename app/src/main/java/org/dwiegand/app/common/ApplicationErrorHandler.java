package org.dwiegand.app.common;

import lombok.extern.slf4j.Slf4j;
import org.dwiegand.commonweb.error.advice.BaseWebExceptionHandler;
import org.dwiegand.commonweb.error.codes.EventCodes;
import org.dwiegand.commonweb.error.codes.StatusCodes;
import org.dwiegand.commonweb.error.exceptions.ApplicationExceptions;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@ControllerAdvice(basePackages = "org.dwiegand.app")
@Slf4j
public class ApplicationErrorHandler
        extends BaseWebExceptionHandler
        implements RequestBodyAdvice, ResponseBodyAdvice<Object>
{

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

        throw ApplicationExceptions.webError(StatusCodes.CLIENT_DATA_ERROR, List.of(EventCodes.VALIDATION_ERROR));
//        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    // Response methods

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return body;
    }
}
