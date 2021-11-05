package seedu.address.commons.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;

public class DateTimeUtil {

    /**
     * Returns a case-insensitive DateTimeFormatter according to the given pattern with a Strict resolver style
     */
    public static DateTimeFormatter buildFormatter(String pattern) {
        return new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern(pattern)
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);
    }
}
