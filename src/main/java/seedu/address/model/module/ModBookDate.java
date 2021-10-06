package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ModBookDate implements Comparable<ModBookDate> {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format of dd/mm/yyyy";
    public static final DateTimeFormatter PARSE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public final LocalTime date;

    /**
     * Constructs a {@code ModBookDate}
     *
     * @param date the string representing date of the ModBookDate
     */
    public ModBookDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalTime.parse(date, PARSE_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date
     */
    public static boolean isValidDate(String test) {
        requireNonNull(test);
        try {
            LocalTime.parse(test, PARSE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
