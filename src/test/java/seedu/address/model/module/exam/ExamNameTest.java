package seedu.address.model.module.exam;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExamNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExamName(null));
    }

    @Test
    public void constructor_invalidExamName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ExamName(invalidName));
    }

    @Test
    public void isValidExamName() {
        // null exam name
        assertThrows(NullPointerException.class, () -> ExamName.isValidExamName(null));

        // invalid exam name
        assertFalse(ExamName.isValidExamName("")); // empty string
        assertFalse(ExamName.isValidExamName(" ")); // spaces only

        // valid exam name
        assertTrue(ExamName.isValidExamName("finals")); // lowercase alphabets only
        assertTrue(ExamName.isValidExamName("reading assessment 2")); // alphanumeric characters
        assertTrue(ExamName.isValidExamName("Mastery Check 1")); // with capital letters
        assertTrue(ExamName.isValidExamName("Practical Examination 1 for Circuits and Systems")); // long names
    }
}
