package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ModBookTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModBookTime(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new ModBookTime(invalidTime));
    }

    @Test
    void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> ModBookTime.isValidTime(null));

        // blank time
        assertFalse(ModBookTime.isValidTime(""));
        assertFalse(ModBookTime.isValidTime(" "));

        // invalid time formats
        assertFalse(ModBookTime.isValidTime("4:00pm")); // use of AM/PM
        assertFalse(ModBookTime.isValidTime("4pm")); // no minutes, use of AM/PM
        assertFalse(ModBookTime.isValidTime("4:00")); // hours using 1 digit and not 2
        assertFalse(ModBookTime.isValidTime("16")); // no minutes
        assertFalse(ModBookTime.isValidTime("25:00")); // hours out of range
        assertFalse(ModBookTime.isValidTime("14:98")); // minutes out of range
        assertFalse(ModBookTime.isValidTime("16:00:01")); // including seconds value
        assertFalse(ModBookTime.isValidTime("16:00:01.1234")); // including seconds and decimal value

        // valid times
        assertTrue(ModBookTime.isValidTime("16:00"));
        assertTrue(ModBookTime.isValidTime("08:00"));
        assertTrue(ModBookTime.isValidTime("23:59"));
    }
}
