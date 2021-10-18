package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
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
        assertFalse(ModBookTime.isValidTime("abcd")); // not a time
        assertFalse(ModBookTime.isValidTime("16")); // no minutes
        assertFalse(ModBookTime.isValidTime("5:00")); // only one digit in hours
        assertFalse(ModBookTime.isValidTime("25:00")); // hours out of range
        assertFalse(ModBookTime.isValidTime("14:98")); // minutes out of range
        assertFalse(ModBookTime.isValidTime("16:00:01")); // including seconds value
        assertFalse(ModBookTime.isValidTime("16:00:01.1234")); // including seconds and decimal value

        // valid times
        assertTrue(ModBookTime.isValidTime("16:00"));
        assertTrue(ModBookTime.isValidTime("08:00"));
        assertTrue(ModBookTime.isValidTime("23:59"));

        // valid time formats
        assertTrue(ModBookTime.isValidTime("23:59"));
        assertTrue(ModBookTime.isValidTime("23.59"));
        assertTrue(ModBookTime.isValidTime("2359"));
        assertTrue(ModBookTime.isValidTime("1PM"));
        assertTrue(ModBookTime.isValidTime("11PM"));
        assertTrue(ModBookTime.isValidTime("1:59PM"));
        assertTrue(ModBookTime.isValidTime("11:59PM"));
        assertTrue(ModBookTime.isValidTime("1.59PM"));
        assertTrue(ModBookTime.isValidTime("11.59PM"));

    }

    @Test
    void tryParse() {
        LocalTime expectedFourPm = LocalTime.parse("16:00");
        LocalTime expectedElevenThirtyAm = LocalTime.parse("11:30");
        // null time
        assertThrows(NullPointerException.class, () -> ModBookTime.tryParse(null));

        // blank time
        assertNull(ModBookTime.tryParse(""));
        assertNull(ModBookTime.tryParse(" "));

        // invalid time formats
        assertNull(ModBookTime.tryParse("14")); // no minutes
        assertNull(ModBookTime.tryParse("25:00")); // hours out of range
        assertNull(ModBookTime.tryParse("14:98")); // minutes out of range
        assertNull(ModBookTime.tryParse("16:00:01")); // including seconds value
        assertNull(ModBookTime.tryParse("16:00:01.1234")); // including seconds and decimal value
        assertNull(ModBookTime.tryParse("abcd")); // not a time

        // valid time formats
        assertEquals(ModBookTime.tryParse("16:00"), expectedFourPm);
        assertEquals(ModBookTime.tryParse("16.00"), expectedFourPm);
        assertEquals(ModBookTime.tryParse("1600"), expectedFourPm);
        assertEquals(ModBookTime.tryParse("4PM"), expectedFourPm);
        assertEquals(ModBookTime.tryParse("04PM"), expectedFourPm);
        assertEquals(ModBookTime.tryParse("4:00PM"), expectedFourPm);
        assertEquals(ModBookTime.tryParse("04:00PM"), expectedFourPm);
        assertEquals(ModBookTime.tryParse("4.00PM"), expectedFourPm);
        assertEquals(ModBookTime.tryParse("04.00PM"), expectedFourPm);

        assertEquals(ModBookTime.tryParse("11:30"), expectedElevenThirtyAm);
        assertEquals(ModBookTime.tryParse("11.30"), expectedElevenThirtyAm);
        assertEquals(ModBookTime.tryParse("1130"), expectedElevenThirtyAm);
        assertEquals(ModBookTime.tryParse("11:30AM"), expectedElevenThirtyAm);
        assertEquals(ModBookTime.tryParse("11.30AM"), expectedElevenThirtyAm);
    }
}
