package seedu.address.model.module.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ExamName {
    public static final String MESSAGE_CONSTRAINTS =
            "Exam name should not be blank";

    /*
     * The first character of the exam must not be a whitespace.
     */
    public static final String VALIDATION_REGEX = "^\\S.*$";

    public final String fullExamName;

    /**
     * Constructs a {@code ExamName}.
     *
     * @param examName A valid exam name.
     */
    public ExamName(String examName) {
        requireNonNull(examName);
        checkArgument(isValidExamName(examName), MESSAGE_CONSTRAINTS);
        fullExamName = examName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidExamName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullExamName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExamName // instanceof handles nulls
                && fullExamName.equals(((ExamName) other).fullExamName)); // state check
    }

    @Override
    public int hashCode() {
        return fullExamName.hashCode();
    }
}
