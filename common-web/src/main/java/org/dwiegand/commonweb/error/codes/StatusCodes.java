package org.dwiegand.commonweb.error.codes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.UtilityClass;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StatusCodes {

    public static final StatusCode CLIENT_DATA_ERROR = new StatusCodeDto(20, "Ошибка при обработке клиентских данных");
    public static final StatusCode APP_INTERNAL_ERROR = new StatusCodeDto(100, "Внутренняя ошибка приложения");
    public static final StatusCode APP_EXTERNAL_ERROR = new StatusCodeDto(101, "Ошибка приложения при работе с внешними системами");
    public static final StatusCode UNEXPECTED_ERROR = new StatusCodeDto(200, "Неожиданна ошибка приложения");

    public static StatusCode from(EventCode event) {
        return new StatusCodeDto(event.getCode(), event.getDescription());
    }

    public static StatusCode from(EventCode event, String description) {
        return new StatusCodeDto(event.getCode(), description);
    }

    @Value
    protected static class StatusCodeDto implements StatusCode {

        int code;

        String description;
    }
}
