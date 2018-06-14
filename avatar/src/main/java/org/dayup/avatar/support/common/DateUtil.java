package org.dayup.avatar.support.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {

    private static ZoneId zone = ZoneId.systemDefault();

    public static LocalDateTime toLocalDateTime(String instantText) {
        return toLocalDateTime(Instant.parse(instantText));
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, zone);
    }


}
