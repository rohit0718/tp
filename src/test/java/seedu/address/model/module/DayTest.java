package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Day.valueOf(null));
    }

    @Test
    public void constructor_invalidDay_throwsIllegalArgumentException() {
        String invalidDay = " ";
        assertThrows(IllegalArgumentException.class, () -> Day.valueOf(invalidDay));
    }


    @Test
    public void find() {
        // invalid Day
        assertThrows(IllegalArgumentException.class, () -> Day.find("shezadhassan22"));
        assertThrows(IllegalArgumentException.class, () -> Day.find(" "));
        assertThrows(IllegalArgumentException.class, () -> Day.find("mondayday"));

        // valid Day
        assertEquals(Day.MONDAY, Day.find("Monday"));
        assertEquals(Day.TUESDAY, Day.find("TUESDAY")); // capitalised
        assertEquals(Day.WEDNESDAY, Day.find("Wed")); // short form
        assertEquals(Day.THURSDAY, Day.find("THURS")); // short form + capitalised
        assertEquals(Day.FRIDAY, Day.find("fri")); // short form + not capitalised
        assertEquals(Day.SATURDAY, Day.find("SatUrDay")); // nonsense
        assertEquals(Day.SUNDAY, Day.find("Sunday"));

    }


    @Test
    public void isValidDay() {
        // null Day
        assertThrows(NullPointerException.class, () -> Day.isValidDay(null));

        // invalid Day
        assertFalse(Day.isValidDay("")); // empty string
        assertFalse(Day.isValidDay(" ")); // spaces only

        // valid Day
        assertTrue(Day.isValidDay("Monday"));
        assertTrue(Day.isValidDay("Tuesday"));
        assertTrue(Day.isValidDay("Wednesday"));
        assertTrue(Day.isValidDay("Thursday"));
        assertTrue(Day.isValidDay("Friday"));
        assertTrue(Day.isValidDay("Saturday"));
        assertTrue(Day.isValidDay("Sunday"));

        // valid Day: non capitalised
        assertTrue(Day.isValidDay("monday"));
        assertTrue(Day.isValidDay("tuesday"));
        assertTrue(Day.isValidDay("wednesday"));
        assertTrue(Day.isValidDay("thursday"));
        assertTrue(Day.isValidDay("friday"));
        assertTrue(Day.isValidDay("saturday"));
        assertTrue(Day.isValidDay("sunday"));

        // valid Day: short form
        assertTrue(Day.isValidDay("mon"));
        assertTrue(Day.isValidDay("tues"));
        assertTrue(Day.isValidDay("wed"));
        assertTrue(Day.isValidDay("thurs"));
        assertTrue(Day.isValidDay("fri"));
        assertTrue(Day.isValidDay("sat"));
        assertTrue(Day.isValidDay("sun"));

        // invalid Day
        assertFalse(Day.isValidDay("-"));
        assertFalse(Day.isValidDay("shezadhassan22"));
    }
}
