package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.storage.JsonAdaptedLesson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.CS2103T_LECTURE;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Day;
import seedu.address.model.module.Link;
import seedu.address.model.module.Venue;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.module.lesson.LessonName;

class JsonAdaptedLessonTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_DAY = "today";
    private static final Optional<String> INVALID_VENUE = Optional.of("  ");
    private static final Optional<String> INVALID_LINK = Optional.of("    ");

    private static final String VALID_NAME = CS2103T_LECTURE.getName().toString();
    private static final String VALID_DAY = CS2103T_LECTURE.getDay().toString();
    private static final JsonAdaptedTimeslot VALID_TIMESLOT = new JsonAdaptedTimeslot(CS2103T_LECTURE.getTimeslot());
    private static final Optional<String> VALID_VENUE = Optional.of("LT15");
    private static final Optional<String> VALID_LINK = CS2103T_LECTURE.getLink().map(Link::toString);
    private static final Optional<String> EMPTY_VENUE = Optional.empty();
    private static final Optional<String> EMPTY_LINK = Optional.empty();

    @Test
    public void toModelType_validLessonDetails_returnsLesson() throws Exception {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(CS2103T_LECTURE);
        assertEquals(CS2103T_LECTURE, lesson.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(INVALID_NAME, VALID_DAY, VALID_TIMESLOT, VALID_VENUE, VALID_LINK);
        String expectedMessage = LessonName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(null, VALID_DAY, VALID_TIMESLOT, VALID_VENUE, VALID_LINK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, INVALID_DAY, VALID_TIMESLOT, VALID_VENUE, VALID_LINK);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullDay_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, null, VALID_TIMESLOT, VALID_VENUE, VALID_LINK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidVenue_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, VALID_DAY, VALID_TIMESLOT, INVALID_VENUE, VALID_LINK);
        String expectedMessage = Venue.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullVenue_success() {
        try {
            JsonAdaptedLesson lesson =
                    new JsonAdaptedLesson(VALID_NAME, VALID_DAY, VALID_TIMESLOT, null, VALID_LINK);
            Lesson readLesson = lesson.toModelType();
            assertEquals(Optional.empty(), readLesson.getVenue());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void toModelType_emptyVenue_success() {
        try {
            JsonAdaptedLesson lesson =
                    new JsonAdaptedLesson(VALID_NAME, VALID_DAY, VALID_TIMESLOT, EMPTY_VENUE, VALID_LINK);
            Lesson readLesson = lesson.toModelType();
            assertEquals(Optional.empty(), readLesson.getVenue());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void toModelType_invalidLink_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_NAME, VALID_DAY, VALID_TIMESLOT, VALID_VENUE, INVALID_LINK);
        String expectedMessage = Link.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullLink_success() {
        try {
            JsonAdaptedLesson lesson =
                    new JsonAdaptedLesson(VALID_NAME, VALID_DAY, VALID_TIMESLOT, VALID_VENUE, null);
            Lesson readLesson = lesson.toModelType();
            assertEquals(Optional.empty(), readLesson.getLink());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void toModelType_emptyLink_success() {
        try {
            JsonAdaptedLesson lesson =
                    new JsonAdaptedLesson(VALID_NAME, VALID_DAY, VALID_TIMESLOT, VALID_VENUE, EMPTY_LINK);
            Lesson readLesson = lesson.toModelType();
            assertEquals(Optional.empty(), readLesson.getLink());
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
