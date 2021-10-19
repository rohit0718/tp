package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LinkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null));
    }

    @Test
    public void constructor_invalidLink_throwsIllegalArgumentException() {
        String invalidLink = " ";
        assertThrows(IllegalArgumentException.class, () -> new Link(invalidLink));
    }

    @Test
    public void isValidLink() {
        // null Link
        assertThrows(NullPointerException.class, () -> Link.isValidLink(null));

        // invalid Link
        assertFalse(Link.isValidLink("")); // empty string
        assertFalse(Link.isValidLink(" ")); // spaces only

        // valid Link
        assertTrue(Link.isValidLink("https://www.youtube.com/watch?v=dE1P4zDhhqw&t=1s"));
        assertTrue(Link.isValidLink("www.google.com"));
        assertTrue(Link.isValidLink("http://www.cool.com.au"));
        assertTrue(Link.isValidLink("http://www.cool.com:81/index.html"));

        // invalid Link
        assertFalse(Link.isValidLink("-")); // one character
        assertFalse(Link.isValidLink("htp://youtube.com"));
        assertFalse(Link.isValidLink("https://wwwyoutubecom/watch?v=dE1P4zDhhqw&t=1s"));
        assertFalse(Link.isValidLink("https://www.youtube.com/ f  f  f"));
        assertFalse(Link.isValidLink("https:/www.youtube.com/watch?v=dE1P4zDhhqw&t=1s"));
        assertFalse(Link.isValidLink("rajesh://www.youtube.com/watch?v=dE1P4zDhhqw&t=1s"));
        assertFalse(Link.isValidLink("dummytext"));
    }
}
