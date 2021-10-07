package seedu.address.model.module;

public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static final String MESSAGE_CONSTRAINTS =
            "Input for Day is not formatted correctly.";

    /**
     * Returns true if a given string is a valid Day.
     */
    public static boolean isValidDay(String dayString) {
        try {
            Day.valueOf(dayString.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
