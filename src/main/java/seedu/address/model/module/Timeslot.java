package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a timeslot in the ModBook.
 * Guarantees: immutable.
 */
public class Timeslot implements Comparable<Timeslot> {

    public static final String MESSAGE_CONSTRAINTS =
            "Start time of the Timeslot should be before the end time";
    public static final String SEPARATOR = " - ";
    public final ModBookTime startTime;
    public final ModBookTime endTime;

    /**
     * Constructs a {@code Timeslot}
     *
     * @param startTime the starting time of the timeslot
     * @param endTime the ending time of the timeslot
     */
    public Timeslot(ModBookTime startTime, ModBookTime endTime) {
        requireNonNull(startTime);
        requireNonNull(endTime);
        checkArgument(isValidTimeslot(startTime, endTime), MESSAGE_CONSTRAINTS);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns true if the given startTime is before the given endTime.
     */
    public static boolean isValidTimeslot(ModBookTime startTime, ModBookTime endTime) {
        requireNonNull(startTime);
        requireNonNull(endTime);
        return startTime.compareTo(endTime) < 0;
    }

    @Override
    public String toString() {
        return startTime + SEPARATOR + endTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timeslot // instanceof handles nulls
                && startTime.equals(((Timeslot) other).startTime)
                && endTime.equals(((Timeslot) other).endTime)); // state check
    }

    /**
     * Compares between two Timeslot objects.
     * Will compare based on startTime - i.e. earlier startTime will be ordered first.
     * If startTimes are the same, will compare based on endTime.
     *
     * @param other the Timeslot to compare with
     * @return a negative integer, zero or a positive integer as this Timeslot is before, at the same starting time
     *         or after the given Timeslot respectively.
     */
    @Override
    public int compareTo(Timeslot other) {
        int startOrder = startTime.compareTo(other.startTime);
        if (startOrder == 0) {
            return endTime.compareTo(other.startTime);
        }
        return startOrder;
    }

    @Override
    public int hashCode() {
        return startTime.hashCode() + endTime.hashCode();
    }
}
