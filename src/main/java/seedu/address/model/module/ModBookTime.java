package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a time in the ModBook.
 * Guarantees: immutable.
 */
public class ModBookTime implements Comparable<ModBookTime> {

    public static final String MESSAGE_CONSTRAINTS =
            "Times should be in the format of hh:mm in 24 hour time";
    public static final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public final LocalTime time;

    /**
     * Constructs a {@code ModBookTime}
     *
     * @param time the string representing time of the ModBookTime
     */
    public ModBookTime(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns true if a given string is a valid time
     */
    public static boolean isValidTime(String timeString) {
        try {
            LocalTime.parse(timeString);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return time.format(PRINT_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModBookTime // instanceof handles nulls
                && time.equals(((ModBookTime) other).time)); // state check
    }

    /**
     * Compares between two ModBookTime objects.
     *
     * @param other the ModBookTime to compare with
     * @return a negative integer, zero or a positive integer as this ModBookTime is before, at the same time
     *         or after the given ModBookTime respectively.
     */
    @Override
    public int compareTo(ModBookTime other) {
        return time.compareTo(other.time);
    }
}
