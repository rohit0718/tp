package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Day {
    MONDAY ("MONDAY", "MON"),
    TUESDAY ("TUESDAY", "TUES", "TUE"),
    WEDNESDAY ("WEDNESDAY", "WED"),
    THURSDAY ("THURSDAY", "THURS", "THUR", "THU"),
    FRIDAY("FRIDAY", "FRI"),
    SATURDAY("SATURDAY", "SAT"),
    SUNDAY("SUNDAY", "SUN");

    public static final String MESSAGE_CONSTRAINTS =
            "Day should be entered as day of the week e.g. Mon, Tuesday, etc.";

    // Attribute to hold the valid inputs
    private final List<String> values;

    // Constructor to set string attribute
    Day(String ...names) {
        requireNonNull(names);
        this.values = Arrays.asList(names);
    }

    /**
     * Returns the corresponding Day for the input string.
     */
    public static Day find(String input) {
        for (Day day: Day.values()) {
            if (day.values.contains(input.toUpperCase().trim())) {
                return day;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Returns true if a given string is a valid Day.
     */
    public static boolean isValidDay(String dayString) {
        try {
            Day.find(dayString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static int getTodayIntValue() {
        return Math.floorMod(LocalDate.now().getDayOfWeek().getValue() - 1, 7);
    }

    public boolean isToday() {
        return this.ordinal() == getTodayIntValue();
    }

    public static class DayComparator implements Comparator<Day> {
        @Override
        public int compare(Day day, Day dayTwo) {
            // get day of week, shift DayOfWeek by 1 so that Monday is 0 and Sunday is 6.
            int currentDay = getTodayIntValue();

            // get ordinal values of Day enums, shift by currentDay
            int dayNum = Math.floorMod(day.ordinal() - currentDay, 7);
            int dayTwoNum = Math.floorMod(dayTwo.ordinal() - currentDay, 7);

            // compare both day values
            return dayNum - dayTwoNum;
        }
    }

    /**
     * Returns Day in readable format (e.g. Monday, Tuesday).
     */
    @Override
    public String toString() {
        String dayString = this.values.get(0);
        return dayString.substring(0, 1) + dayString.substring(1).toLowerCase();
    }
}
