package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VenueTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Venue(null));
    }

    @Test
    public void constructor_invalidVenue_throwsIllegalArgumentException() {
        String invalidVenue = "";
        assertThrows(IllegalArgumentException.class, () -> new Venue(invalidVenue));
    }

    @Test
    public void isValidVenue() {
        // null venue
        assertThrows(NullPointerException.class, () -> Venue.isValidVenue(null));

        // invalid venue
        assertFalse(Venue.isValidVenue("")); // empty string
        assertFalse(Venue.isValidVenue(" ")); // spaces only

        // valid venue
        assertTrue(Venue.isValidVenue("peter jack hall")); // lowercase alphabets only
        assertTrue(Venue.isValidVenue("298103")); // numbers only
        assertTrue(Venue.isValidVenue("mph hall 2")); // alphanumeric characters
        assertTrue(Venue.isValidVenue("University Sports Centre")); // with capital letters
        assertTrue(Venue.isValidVenue("National University of Singapore, School of Computing")); // long names
    }
}
