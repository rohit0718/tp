package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.DateTimeUtil.buildFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a date in the ModBook.
 * Guarantees: immutable.
 */
public class ModBookDate implements Comparable<ModBookDate> {
    public static final String MESSAGE_CONSTRAINTS =
            "Invalid date format. Please refer to the User Guide for valid date formats.";
    public static final DateTimeFormatter[] PARSE_FORMATTERS = new DateTimeFormatter[] {
        buildFormatter("dd/MM/uuuu"),
        buildFormatter("dd-MM-uuuu"),
        buildFormatter("dd.MM.uuuu"),
        buildFormatter("ddMMuuuu"),
        buildFormatter("dd MM uuuu"),
        buildFormatter("dd LLLL uuuu"),
        buildFormatter("dd LLL uuuu"),
        buildFormatter("d LLL uuuu"),
        buildFormatter("d LLLL uuuu")
    };
    public static final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public final LocalDate date;

    /**
     * Constructs a {@code ModBookDate}
     *
     * @param date the string representing date of the ModBookDate
     */
    public ModBookDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = parseDate(date);
    }

    private ModBookDate(LocalDate date) {
        this.date = date;
    }

    public ModBookDate deepCopy() {
        return new ModBookDate(date);
    }

    /**
     * Returns true if a given string is a valid date
     */
    public static boolean isValidDate(String test) {
        requireNonNull(test);
        return parseDate(test) != null;
    }

    /**
     * Tries to parse a given string with various DateTimeFormatters.
     * Returns a LocalTime if parsing was successful,  null otherwise.
     */
    public static LocalDate parseDate(String test) {
        requireNonNull(test);
        LocalDate result = null;
        for (DateTimeFormatter f : PARSE_FORMATTERS) {
            try {
                result = LocalDate.parse(test, f.withResolverStyle(ResolverStyle.STRICT));
                break; // short circuit once valid formatter is found
            } catch (DateTimeParseException e) {
                // do nothing
            }
        }
        return result;
    }

    /**
     * Checks if ModBookDate is today
     */
    public boolean isToday() {
        return date.compareTo(LocalDate.now()) == 0;
    }

    /**
     * Checks if ModBookDate is in the future
     */
    public boolean isFuture() {
        return date.compareTo(LocalDate.now()) > 0;
    }

    @Override
    public String toString() {
        return date.format(PRINT_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModBookDate // instanceof handles nulls
                && date.equals(((ModBookDate) other).date)); // state check
    }

    /**
     * Compares between two ModBookDate objects.
     *
     * @param otherDate the ModBookDate to compare with
     * @return a negative integer, zero or a positive integer as this ModBookDate is before, at the same date
     *         or after the given ModBookDate respectively.
     */
    @Override
    public int compareTo(ModBookDate otherDate) {
        return date.compareTo(otherDate.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
