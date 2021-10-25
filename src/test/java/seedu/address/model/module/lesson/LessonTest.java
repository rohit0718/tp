package seedu.address.model.module.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Day;
import seedu.address.model.module.Link;
import seedu.address.model.module.ModBookTime;
import seedu.address.model.module.Timeslot;
import seedu.address.model.module.Venue;

public class LessonTest {
    private static final LessonName LESSON_NAME = new LessonName("Lecture");
    private static final Day DAY_1 = Day.values()[Day.getTodayIntValue() + 1];
    private static final ModBookTime START_TIME_2 = new ModBookTime("09:30");
    private static final ModBookTime END_TIME_2 = new ModBookTime("11:30");
    private static final Timeslot TIMESLOT_2 = new Timeslot(START_TIME_2, END_TIME_2);

    private static final Day DAY_2 = Day.values()[Day.getTodayIntValue() + 2];
    private static final ModBookTime START_TIME = new ModBookTime("09:00");
    private static final ModBookTime END_TIME = new ModBookTime("11:00");
    private static final Timeslot TIMESLOT = new Timeslot(START_TIME, END_TIME);

    private static final Optional<Venue> VENUE = Optional.of(new Venue("COM1-B102"));
    private static final Optional<Link> LINK = Optional.of(new Link("http://nus-sg.zoom.us/123456789"));
    private static final Lesson LESSON = new Lesson(LESSON_NAME, DAY_2, TIMESLOT, VENUE, LINK);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson(
                LESSON_NAME, null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Lesson(
                LESSON_NAME, DAY_2, null, null, null));
        assertThrows(NullPointerException.class, () -> new Lesson(
                LESSON_NAME, null, TIMESLOT, VENUE, LINK));
        assertThrows(NullPointerException.class, () -> new Lesson(
                null, null, TIMESLOT, VENUE, LINK));
        assertThrows(NullPointerException.class, () -> new Lesson(
                null, null, null, null, null));
    }

    @Test
    void isSameLesson() {
        // Same object
        assertTrue(LESSON.isSameLesson(LESSON));

        // Different object, same name
        assertTrue(LESSON.isSameLesson(new Lesson(LESSON_NAME, DAY_2, TIMESLOT, VENUE, LINK)));

        // Different name
        assertFalse(LESSON.isSameLesson(new Lesson(new LessonName("Finals"), DAY_2, TIMESLOT, VENUE, LINK)));
    }

    @Test
    void compareTo() {
        // different date, same time
        assertTrue(LESSON.compareTo(new Lesson(LESSON_NAME, DAY_1, TIMESLOT, VENUE, LINK)) > 0);

        // same date, different time
        assertTrue(LESSON.compareTo(new Lesson(LESSON_NAME, DAY_2, TIMESLOT_2, VENUE, LINK)) < 0);

        // different date, different time
        assertTrue(LESSON.compareTo(new Lesson(LESSON_NAME, DAY_1, TIMESLOT_2, VENUE, LINK)) > 0);

        // same date, same time
        assertEquals(LESSON.compareTo(new Lesson(LESSON_NAME, DAY_2, TIMESLOT, VENUE, LINK)), 0);
    }
}
