package org.dwiegand.commonweb.error.codes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.UtilityClass;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventCodes {

    public static final EventCode VALIDATION_ERROR = new EventCodeDto(20, "Ошибка при валидации данных");
    public static final EventCode DATA_SAVE_ERROR = new EventCodeDto(21, "Ошибка загрузки/обновления данных");
    public static final EventCode TECH_ERROR = new EventCodeDto(100, "Техническая ошибка");

    public static EventCode from(EventCode event) {
        return new EventCodeDto(event.getCode(), event.getDescription());
    }

    public static EventCode from(EventCode event, String description) {
        return new EventCodeDto(event.getCode(), description);
    }

    @Value
    protected static class EventCodeDto implements EventCode {

        int code;

        String description;
    }
}
