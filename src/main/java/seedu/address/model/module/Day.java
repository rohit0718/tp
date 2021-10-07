package seedu.address.model.module;

public enum Day {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;

    public static final String MESSAGE_CONSTRAINTS =
            "Input for Day is not formatted correctly.";

    public static boolean isValidDay(String dayString) {
        try {
            Day.valueOf(dayString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
