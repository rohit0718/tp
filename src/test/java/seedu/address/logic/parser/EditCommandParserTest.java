package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.EditCommandParser.MESSAGE_WRONG_VIEW_DETAILS;
import static seedu.address.logic.parser.EditCommandParser.MESSAGE_WRONG_VIEW_SUMMARY;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_NO_INDEXES_FOUND;
import static seedu.address.testutil.TypicalExams.PHYSICAL_FINALS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalLessons.CS2103T_LECTURE_WITH_VENUE;
import static seedu.address.testutil.TypicalModules.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditExamCommand;
import seedu.address.logic.commands.edit.EditExamCommand.EditExamDescriptor;
import seedu.address.logic.commands.edit.EditLessonCommand;
import seedu.address.logic.commands.edit.EditLessonCommand.EditLessonDescriptor;
import seedu.address.logic.commands.edit.EditModCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.builders.ExamBuilder;
import seedu.address.testutil.builders.LessonBuilder;
import seedu.address.testutil.builders.ModuleBuilder;

public class EditCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_MODULE_CODE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_MODULE_CODE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_MODULE_CODE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingKeyword_throwsParseException() {
        assertParseFailure(parser, "venue",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        assertParseFailure(parser, "mod lesson", MESSAGE_NO_INDEXES_FOUND);
    }

    @Test
    public void parse_validArgs_returnsEditCommand() {
        Module module = new ModuleBuilder(CS2103T).withDefaultName().build();
        EditModCommand.EditModDescriptor editModDescriptor = new EditModCommand.EditModDescriptor();
        editModDescriptor.setModuleCode(module.getCode());
        if (module.getName().isPresent()) {
            editModDescriptor.setModuleName(module.getName().get());
        }

        Lesson lesson = new LessonBuilder(CS2103T_LECTURE_WITH_VENUE).build();
        EditLessonDescriptor editLessonDescriptor = new EditLessonCommand.EditLessonDescriptor();
        editLessonDescriptor.setName(lesson.getName());
        editLessonDescriptor.setDay(lesson.getDay());
        editLessonDescriptor.setTimeslot(lesson.getTimeslot());
        if (lesson.getLink().isPresent()) {
            editLessonDescriptor.setLink(lesson.getLink().get());
        }
        if (lesson.getVenue().isPresent()) {
            editLessonDescriptor.setVenue(lesson.getVenue().get());
        }

        Exam exam = new ExamBuilder(PHYSICAL_FINALS).build();
        EditExamDescriptor editExamDescriptor = new EditExamCommand.EditExamDescriptor();
        editExamDescriptor.setName(exam.getName());
        editExamDescriptor.setDate(exam.getDate());
        editExamDescriptor.setTimeslot(exam.getTimeslot());
        if (exam.getLink().isPresent()) {
            editExamDescriptor.setLink(exam.getLink().get());
        }
        if (exam.getVenue().isPresent()) {
            editExamDescriptor.setVenue(exam.getVenue().get());
        }

        // no leading and trailing whitespaces
        EditCommand expectedEditModCommand = new EditModCommand(INDEX_FIRST_MODULE, editModDescriptor);
        assertParseSuccess(parser, " mod " + INDEX_FIRST_MODULE.getOneBased() + " "
                + PersonUtil.getEditModDescriptorDetails(editModDescriptor), expectedEditModCommand);

        EditCommand expectedEditLessonCommand = new EditLessonCommand(INDEX_FIRST_LESSON, module.getCode(),
                editLessonDescriptor);
        assertParseSuccess(parser, " lesson " + INDEX_FIRST_LESSON.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditLessonDescriptorDetails(editLessonDescriptor),
                GuiState.DETAILS, expectedEditLessonCommand);

        EditCommand expectedEditExamCommand = new EditExamCommand(INDEX_FIRST_EXAM, module.getCode(),
                editExamDescriptor);
        assertParseSuccess(parser, " exam " + INDEX_FIRST_EXAM.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditExamDescriptorDetails(editExamDescriptor),
                GuiState.DETAILS, expectedEditExamCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " mod " + INDEX_FIRST_MODULE.getOneBased() + " "
                + PersonUtil.getEditModDescriptorDetails(editModDescriptor) + " \n \t ", expectedEditModCommand);

        // edit mod command must only work in the summary gui state
        assertParseSuccess(parser, " mod " + INDEX_FIRST_MODULE.getOneBased() + " "
                + PersonUtil.getEditModDescriptorDetails(editModDescriptor), GuiState.SUMMARY, expectedEditModCommand);
        assertParseFailure(parser, " mod " + INDEX_FIRST_MODULE.getOneBased() + " "
                        + PersonUtil.getEditModDescriptorDetails(editModDescriptor), GuiState.LESSONS,
                MESSAGE_WRONG_VIEW_SUMMARY);
        assertParseFailure(parser, " mod " + INDEX_FIRST_MODULE.getOneBased() + " "
                        + PersonUtil.getEditModDescriptorDetails(editModDescriptor),
                GuiState.EXAMS, MESSAGE_WRONG_VIEW_SUMMARY);
        assertParseFailure(parser, " mod " + INDEX_FIRST_MODULE.getOneBased() + " "
                        + PersonUtil.getEditModDescriptorDetails(editModDescriptor),
                GuiState.DETAILS, MESSAGE_WRONG_VIEW_SUMMARY);

        // edit exam command must only work in the details gui state
        assertParseSuccess(parser, " exam " + INDEX_FIRST_EXAM.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditExamDescriptorDetails(editExamDescriptor),
                GuiState.DETAILS, expectedEditExamCommand);
        assertParseFailure(parser, " exam " + INDEX_FIRST_EXAM.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditExamDescriptorDetails(editExamDescriptor),
                GuiState.SUMMARY, MESSAGE_WRONG_VIEW_DETAILS);
        assertParseFailure(parser, " exam " + INDEX_FIRST_EXAM.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditExamDescriptorDetails(editExamDescriptor),
                GuiState.LESSONS, MESSAGE_WRONG_VIEW_DETAILS);
        assertParseFailure(parser, " exam " + INDEX_FIRST_EXAM.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditExamDescriptorDetails(editExamDescriptor),
                GuiState.EXAMS, MESSAGE_WRONG_VIEW_DETAILS);

        // edit lesson command must only work in the details gui state
        assertParseSuccess(parser, " lesson " + INDEX_FIRST_LESSON.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditLessonDescriptorDetails(editLessonDescriptor),
                GuiState.DETAILS, expectedEditLessonCommand);
        assertParseFailure(parser, " lesson " + INDEX_FIRST_LESSON.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditLessonDescriptorDetails(editLessonDescriptor),
                GuiState.SUMMARY, MESSAGE_WRONG_VIEW_DETAILS);
        assertParseFailure(parser, " lesson " + INDEX_FIRST_LESSON.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditLessonDescriptorDetails(editLessonDescriptor),
                GuiState.LESSONS, MESSAGE_WRONG_VIEW_DETAILS);
        assertParseFailure(parser, " lesson " + INDEX_FIRST_LESSON.getOneBased() + " " + PREFIX_CODE
                        + module.getCode() + " " + PersonUtil.getEditLessonDescriptorDetails(editLessonDescriptor),
                GuiState.EXAMS, MESSAGE_WRONG_VIEW_DETAILS);
    }

}
