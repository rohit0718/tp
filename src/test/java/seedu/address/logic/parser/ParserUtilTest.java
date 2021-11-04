package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_NO_INDEXES_FOUND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_MAX_LIMIT;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Day;
import seedu.address.model.module.Link;
import seedu.address.model.module.ModBookDate;
import seedu.address.model.module.ModBookTime;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.Timeslot;
import seedu.address.model.module.Venue;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.exam.ExamName;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.module.lesson.LessonName;

public class ParserUtilTest {
    private static final String INVALID_LINK = " ";
    private static final String INVALID_TIME = "33:00PM";
    private static final String INVALID_DATE = "20/July/2022";
    private static final String INVALID_VENUE = " ";
    private static final String INVALID_EXAM_NAME = " ";
    private static final String INVALID_LESSON_NAME = " ";
    private static final String INVALID_MODULE_CODE = "C2030S";
    private static final String INVALID_MODULE_NAME = " ";
    private static final String INVALID_FIRST_ARG = "shez";
    private static final String INVALID_INDEX_SUBSTRING = "999999999-9999999";
    private static final String INVALID_INDEX_NEGATIVE_OVERFLOW = "-9999999999999";
    private static final String INVALID_INDEX_POSITIVE_OVERFLOW = "9999999999999";

    private static final String VALID_LINK = "https://www.google.com/";
    private static final String VALID_TIME_1 = "09:00";
    private static final String VALID_TIME_2 = "11am";
    private static final ModBookTime VALID_MBTIME_1 = new ModBookTime(VALID_TIME_1);
    private static final ModBookTime VALID_MBTIME_2 = new ModBookTime(VALID_TIME_2);
    private static final String VALID_DATE = "13/12/2021";
    private static final String VALID_VENUE = "University Sports Centre";
    private static final String VALID_EXAM_NAME = "Midterms";
    private static final String VALID_LESSON_NAME = "Lecture";
    private static final String VALID_MODULE_CODE = "CS2103T";
    private static final String VALID_MODULE_NAME = "Software Engineering";
    private static final String VALID_FIRST_ARG_MOD = "mod";
    private static final String VALID_FIRST_ARG_EXAM = "exam";
    private static final String VALID_FIRST_ARG_LESSON = "lesson";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseLastIndex_invalidInput_throwsParseException() {
        // No integer values
        assertThrows(ParseException.class, MESSAGE_NO_INDEXES_FOUND, () ->
                ParserUtil.parseLastIndex(INVALID_FIRST_ARG));

        // No integer values (Negative numbers used as substring)
        assertThrows(ParseException.class, MESSAGE_NO_INDEXES_FOUND, () ->
                ParserUtil.parseLastIndex(INVALID_INDEX_SUBSTRING));

        // 0 integer value
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseLastIndex("0"));

        // Negative integer value
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseLastIndex("-1"));

        // Positive integer above index limit
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseLastIndex(String.valueOf(ParserUtil.INDEX_LIMIT + 1)));

        // Negative integer overflow
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseLastIndex(INVALID_INDEX_NEGATIVE_OVERFLOW));

        // Positive integer overflow
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseLastIndex(INVALID_INDEX_POSITIVE_OVERFLOW));

        // Mix of invalid integer values and non integer values
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseLastIndex("-1 a"));

        // Mix of integer values ending with invalid value
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseLastIndex("1 3 -1"));
    }

    @Test
    public void parseLastIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseLastIndex("1"));

        // Max index value
        assertEquals(INDEX_MAX_LIMIT, ParserUtil.parseLastIndex(String.valueOf(ParserUtil.INDEX_LIMIT)));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseLastIndex("  1  "));

        // Ending non integer values
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseLastIndex("1 a b"));

        // Starting non integer values
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseLastIndex("a b 1"));

        // Multiple integer values
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseLastIndex("3 2 1"));

        // Mix of valid and invalid integer values
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseLastIndex("-1 0 1"));

        // Mix of valid integer values and non integer values
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseLastIndex("2 1 a"));
    }

    @Test
    public void parseLink_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLink(null));
    }

    @Test
    public void parseLink_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLink(INVALID_LINK));
    }

    @Test
    public void parseLink_validValueWithoutWhitespace_returnsLink() throws Exception {
        Link expectedLink = new Link(VALID_LINK);
        assertEquals(expectedLink, ParserUtil.parseLink(VALID_LINK));
    }

    @Test
    public void parseLink_validValueWithWhitespace_returnsLink() throws Exception {
        String linkWithWhiteSpace = WHITESPACE + VALID_LINK + WHITESPACE;
        Link expectedLink = new Link(VALID_LINK);
        assertEquals(expectedLink, ParserUtil.parseLink(linkWithWhiteSpace));
    }

    @Test
    public void parseTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTime(null));
    }

    @Test
    public void parseTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTime(INVALID_TIME));
    }

    @Test
    public void parseTime_validValueWithoutWhitespace_returnsTime() throws Exception {
        ModBookTime expectedTime = new ModBookTime(VALID_TIME_1);
        assertEquals(expectedTime, ParserUtil.parseTime(VALID_TIME_1));
    }

    @Test
    public void parseTime_validValueWithWhitespace_returnsTrimmedTime() throws Exception {
        String timeWithWhitespace = WHITESPACE + VALID_TIME_1 + WHITESPACE;
        ModBookTime expectedTime = new ModBookTime(VALID_TIME_1);
        assertEquals(expectedTime, ParserUtil.parseTime(timeWithWhitespace));
    }

    @Test
    public void parseTimeslot_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTimeslot(VALID_TIME_1, null));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTimeslot(null, VALID_TIME_2));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTimeslot(null, null));
    }

    @Test
    public void parseTimeSlot_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTimeslot(VALID_TIME_1, VALID_TIME_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseTimeslot(VALID_TIME_2, VALID_TIME_1));
    }

    @Test
    public void parseTimeslot_validValueWithoutWhitespace_returnsTimeslot() throws Exception {
        Timeslot expectedTimeslot = new Timeslot(VALID_MBTIME_1, VALID_MBTIME_2);
        assertEquals(expectedTimeslot, ParserUtil.parseTimeslot(VALID_TIME_1, VALID_TIME_2));
    }

    @Test
    public void parseTimeslot_validValueWithWhitespace_returnsTrimmedTimeslot() throws Exception {
        String timeWithWhitespaceOne = WHITESPACE + VALID_TIME_1 + WHITESPACE;
        String timeWithWhitespaceTwo = WHITESPACE + VALID_TIME_2 + WHITESPACE;
        Timeslot expectedTimeslot = new Timeslot(VALID_MBTIME_1, VALID_MBTIME_2);
        assertEquals(expectedTimeslot, ParserUtil.parseTimeslot(timeWithWhitespaceOne, timeWithWhitespaceTwo));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate(null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        ModBookDate expectedDate = new ModBookDate(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        ModBookDate expectedDate = new ModBookDate(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
    }

    @Test
    public void parseVenue_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVenue(null));
    }

    @Test
    public void parseVenue_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVenue(INVALID_VENUE));
    }

    @Test
    public void parseVenue_validValueWithoutWhitespace_returnsVenue() throws Exception {
        Venue expectedVenue = new Venue(VALID_VENUE);
        assertEquals(expectedVenue, ParserUtil.parseVenue(VALID_VENUE));
    }

    @Test
    public void parseVenue_validValueWithWhitespace_returnsVenue() throws Exception {
        String venueWithWhiteSpace = WHITESPACE + VALID_VENUE + WHITESPACE;
        Venue expectedVenue = new Venue(VALID_VENUE);
        assertEquals(expectedVenue, ParserUtil.parseVenue(venueWithWhiteSpace));
    }

    @Test
    public void parseExamName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExamName(null));
    }

    @Test
    public void parseExamName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExamName(INVALID_EXAM_NAME));
    }

    @Test
    public void parseExamName_validValueWithoutWhitespace_returnsExamName() throws Exception {
        ExamName expectedExamName = new ExamName(VALID_EXAM_NAME);
        assertEquals(expectedExamName, ParserUtil.parseExamName(VALID_EXAM_NAME));
    }

    @Test
    public void parseExamName_validValueWithWhitespace_returnsExamName() throws Exception {
        String examNameWithWhiteSpace = WHITESPACE + VALID_EXAM_NAME + WHITESPACE;
        ExamName expectedExamName = new ExamName(VALID_EXAM_NAME);
        assertEquals(expectedExamName, ParserUtil.parseExamName(examNameWithWhiteSpace));
    }

    @Test
    public void parseExam_null_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> new Exam(
                new ExamName(VALID_EXAM_NAME), new ModBookDate(VALID_DATE), null, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Exam(
                new ExamName(VALID_EXAM_NAME), null, null,
                Optional.of(new Venue(VALID_VENUE)), Optional.of(new Link(VALID_LINK))));
        Assertions.assertThrows(NullPointerException.class, () -> new Exam(
                null, null, null, null, null));
    }

    @Test
    public void parseLessonName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLessonName(null));
    }

    @Test
    public void parseLessonName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLessonName(INVALID_LESSON_NAME));
    }

    @Test
    public void parseLessonName_validValueWithoutWhitespace_returnsLessonName() throws Exception {
        LessonName expectedLessonName = new LessonName(VALID_LESSON_NAME);
        assertEquals(expectedLessonName, ParserUtil.parseLessonName(VALID_LESSON_NAME));
    }

    @Test
    public void parseLessonName_validValueWithWhitespace_returnsLessonName() throws Exception {
        String lessonNameWithWhiteSpace = WHITESPACE + VALID_LESSON_NAME + WHITESPACE;
        LessonName expectedLessonName = new LessonName(VALID_LESSON_NAME);
        assertEquals(expectedLessonName, ParserUtil.parseLessonName(lessonNameWithWhiteSpace));
    }

    @Test
    public void parseLesson_null_throwsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> new Lesson(
                new LessonName(VALID_LESSON_NAME), Day.MONDAY, null, null, null));
        Assertions.assertThrows(NullPointerException.class, () -> new Lesson(
                new LessonName(VALID_LESSON_NAME), null, null,
                Optional.of(new Venue(VALID_VENUE)), Optional.of(new Link(VALID_LINK))));
        Assertions.assertThrows(NullPointerException.class, () -> new Lesson(
                null, null, null, null, null));
    }

    @Test
    public void parseLesson_validValues_returnsLesson() throws Exception {
        LessonName lessonName = new LessonName("Lecture");
        Day day = Day.MONDAY;
        Timeslot timeslot = new Timeslot(VALID_MBTIME_1, VALID_MBTIME_2);
        Lesson expectedLesson = new Lesson(lessonName, day, timeslot, Optional.of(new Venue(VALID_VENUE)),
                Optional.of(new Link(VALID_LINK)));
        assertEquals(expectedLesson, ParserUtil.parseLesson(
                "Lecture", "Monday", "09:00", "11:00",
                Optional.of(VALID_VENUE), Optional.of(VALID_LINK)));
    }

    @Test
    public void parseModuleCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleCode(null));
    }

    @Test
    public void parseModuleCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleCode(INVALID_MODULE_CODE));
    }

    @Test
    public void parseModuleCode_validValueWithoutWhitespace_returnsModuleCode() throws Exception {
        ModuleCode expectedModuleCode = new ModuleCode(VALID_MODULE_CODE);
        assertEquals(expectedModuleCode, ParserUtil.parseModuleCode(VALID_MODULE_CODE));
    }

    @Test
    public void parseModuleCode_validValueWithWhitespace_returnsTrimmedModuleCode() throws Exception {
        String moduleCodeWithWhitespace = WHITESPACE + VALID_MODULE_CODE + WHITESPACE;
        ModuleCode expectedModuleCode = new ModuleCode(VALID_MODULE_CODE);
        assertEquals(expectedModuleCode, ParserUtil.parseModuleCode(moduleCodeWithWhitespace));
    }

    @Test
    public void parseModuleName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleName(null));
    }

    @Test
    public void parseModuleName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleName(INVALID_MODULE_NAME));
    }

    @Test
    public void parseModuleName_validValueWithoutWhitespace_returnsModuleName() throws Exception {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULE_NAME);
        assertEquals(expectedModuleName, ParserUtil.parseModuleName(VALID_MODULE_NAME));
    }

    @Test
    public void parseModuleName_validValueWithWhitespace_returnsTrimmedModuleName() throws Exception {
        String moduleNameWithWhitespace = WHITESPACE + VALID_MODULE_NAME + WHITESPACE;
        ModuleName expectedModuleName = new ModuleName(VALID_MODULE_NAME);
        assertEquals(expectedModuleName, ParserUtil.parseModuleName(moduleNameWithWhitespace));
    }

    @Test
    public void parseModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModule(null, null));
    }

    @Test
    public void parseModule_validValues_returnsModule() throws Exception {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE);
        ModuleName moduleName = new ModuleName(VALID_MODULE_NAME);
        Module expectedModule = new Module(moduleCode, Optional.of(moduleName));
        assertEquals(expectedModule, ParserUtil.parseModule(VALID_MODULE_CODE, Optional.of(VALID_MODULE_NAME)));
    }

    @Test
    public void parseFirstArg_null_throwsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFirstArg(null, null));
    }

    @Test
    public void parseFirstArg_validValues_returnsValidResults() throws Exception {
        assertEquals(Type.MOD, ParserUtil.parseFirstArg(VALID_FIRST_ARG_MOD, null));
        assertEquals(Type.LESSON, ParserUtil.parseFirstArg(VALID_FIRST_ARG_LESSON, null));
        assertEquals(Type.EXAM, ParserUtil.parseFirstArg(VALID_FIRST_ARG_EXAM, null));
    }

    @Test
    public void parseFirstArg_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseFirstArg(INVALID_FIRST_ARG, null));
    }
}
