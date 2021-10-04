package seedu.address.model.module;

import java.time.LocalTime;

/**
 * Represents a timeslot in the ModBook.
 * Guarantees: immutable.
 */
public class Timeslot implements Comparable<Timeslot> {

    public final LocalTime startTime;
    public final LocalTime endTime;

    /**
     * Constructs a {@code Timeslot}
     *
     * @param startTime the starting time of the timeslot
     * @param endTime the ending time of the timeslot
     */
    public Timeslot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return startTime + "-" + endTime;
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
     *
     * @param other the Timeslot to compare with
     * @return a negative integer, zero or a positive integer as this Timeslot is before, at the same starting time
     *         or after the given Timeslot respectively.
     */
    @Override
    public int compareTo(Timeslot other) {
        return startTime.compareTo(other.startTime);
    }
}
