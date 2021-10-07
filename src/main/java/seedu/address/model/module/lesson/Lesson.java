package seedu.address.model.module.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.module.Day;
import seedu.address.model.module.Link;
import seedu.address.model.module.Timeslot;
import seedu.address.model.person.Name;

public class Lesson implements Comparable<Lesson> {
    private final Name name;
    private final Day day;
    private final Timeslot timeslot;
    private final Optional<String> venue;
    private final Optional<Link> link;

    /**
     * Constructs a {@code Lesson}
     *
     * @param name     Name of lesson
     * @param day      Date of lesson
     * @param timeslot Timeslot of the lesson
     * @param venue    Optional venue for the lesson
     * @param link     Optional link for the lesson
     */
    public Lesson(Name name, Day day, Timeslot timeslot, Optional<String> venue, Optional<Link> link) {
        requireAllNonNull(name, day, timeslot, venue, link);
        this.name = name;
        this.day = day;
        this.timeslot = timeslot;
        this.venue = venue;
        this.link = link;
    }

    public Name getName() {
        return name;
    }

    public Day getDay() {
        return day;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public Optional<String> getVenue() {
        return venue;
    }

    public Optional<Link> getLink() {
        return link;
    }

    /**
     * Returns true if both Lessons have the same name.
     * This defines a weaker notion of equality between two Lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getName().equals(getName());
    }

    /**
     * Returns true if both Lessons have the same identity and data fields.
     * This defines a stronger notion of equality between two Lessons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getName().equals(getName())
                && otherLesson.getDay().equals(getDay())
                && otherLesson.getTimeslot().equals(getTimeslot())
                && otherLesson.getVenue().equals(getVenue())
                && otherLesson.getLink().equals(getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, day, timeslot, venue, link);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Day: ")
                .append(getDay())
                .append("; Timeslot: ")
                .append(getTimeslot());
        if (venue.isPresent()) {
            builder.append("; Venue: ")
                    .append(getVenue());
        }
        if (link.isPresent()) {
            builder.append("; Link: ")
                    .append(getLink());
        }
        return builder.toString();
    }

    /**
     * Compares between two Lesson objects.
     * Will compare based on date - i.e. earlier date will be ordered first.
     * If dates are the same, will compare based on timeslot.
     *
     * @param otherLesson the Lesson to compare with
     * @return a negative integer, zero or a positive integer as this Lesson is before, at the same starting time
     * or after the given Lesson respectively.
     */
    @Override
    public int compareTo(Lesson otherLesson) {
        int dateOrder = day.compareTo(otherLesson.day);
        if (dateOrder == 0) {
            return timeslot.compareTo(otherLesson.timeslot);
        }
        return dateOrder;
    }
}
