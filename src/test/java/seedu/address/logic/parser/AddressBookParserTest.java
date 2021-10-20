package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.add.AddModCommand;
import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.commands.delete.DeleteExamCommand;
import seedu.address.logic.commands.delete.DeleteLessonCommand;
import seedu.address.logic.commands.delete.DeleteModCommand;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditExamCommand;
import seedu.address.logic.commands.edit.EditExamCommand.EditExamDescriptor;
import seedu.address.logic.commands.edit.EditLessonCommand;
import seedu.address.logic.commands.edit.EditLessonCommand.EditLessonDescriptor;
import seedu.address.logic.commands.edit.EditModCommand;
import seedu.address.logic.commands.edit.EditModCommand.EditModDescriptor;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.builders.ExamBuilder;
import seedu.address.testutil.builders.LessonBuilder;
import seedu.address.testutil.builders.ModuleBuilder;

public class AddressBookParserTest {

    private static final GuiState DEFAULT_STATE = GuiState.SUMMARY;

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Module module = new ModuleBuilder().build();
        AddModCommand command = (AddModCommand) parser.parseCommand(PersonUtil.getAddCommand(module), DEFAULT_STATE);
        assertEquals(new AddModCommand(module), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, DEFAULT_STATE) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", DEFAULT_STATE) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        Module module = new ModuleBuilder().build();
        DeleteCommand deleteModCommand = (DeleteModCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " mod " + INDEX_FIRST_MODULE.getOneBased(), DEFAULT_STATE);
        assertEquals(new DeleteModCommand(INDEX_FIRST_MODULE), deleteModCommand);

        DeleteCommand deleteLessonCommand = (DeleteLessonCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " lesson "
                        + INDEX_FIRST_LESSON.getOneBased() + " "
                        + PREFIX_CODE + module.getCode(), GuiState.DETAILS);
        assertEquals(new DeleteLessonCommand(INDEX_FIRST_LESSON), deleteLessonCommand);

        DeleteCommand deleteExamCommand = (DeleteExamCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " exam "
                        + INDEX_FIRST_EXAM.getOneBased() + " "
                        + PREFIX_CODE + module.getCode(), GuiState.DETAILS);
        assertEquals(new DeleteExamCommand(INDEX_FIRST_EXAM), deleteExamCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Module module = new ModuleBuilder().withDefaultName().build();
        EditModDescriptor editModDescriptor = new EditModDescriptor();
        editModDescriptor.setModuleCode(module.getCode());
        if (module.getName().isPresent()) {
            editModDescriptor.setModuleName(module.getName().get());
        }
        EditCommand editModCommand = (EditModCommand) parser.parseCommand(EditCommand.COMMAND_WORD
                + " mod " + INDEX_FIRST_MODULE.getOneBased() + " "
                + PersonUtil.getEditModDescriptorDetails(editModDescriptor), DEFAULT_STATE);
        assertEquals(new EditModCommand(INDEX_FIRST_MODULE, editModDescriptor), editModCommand);

        Lesson lesson = new LessonBuilder().build();
        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        editLessonDescriptor.setName(lesson.getName());
        editLessonDescriptor.setDay(lesson.getDay());
        editLessonDescriptor.setTimeslot(lesson.getTimeslot());
        if (lesson.getLink().isPresent()) {
            editLessonDescriptor.setLink(lesson.getLink().get());
        }
        if (lesson.getVenue().isPresent()) {
            editLessonDescriptor.setVenue(lesson.getVenue().get());
        }
        EditCommand editLessonCommand = (EditLessonCommand) parser.parseCommand(EditCommand.COMMAND_WORD
                + " lesson " + INDEX_FIRST_LESSON.getOneBased() + " " + PREFIX_CODE + module.getCode() + " "
                + PersonUtil.getEditLessonDescriptorDetails(editLessonDescriptor), GuiState.DETAILS);
        assertEquals(new EditLessonCommand(INDEX_FIRST_LESSON, editLessonDescriptor),
                editLessonCommand);

        Exam exam = new ExamBuilder().build();
        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
        editExamDescriptor.setName(exam.getName());
        editExamDescriptor.setDate(exam.getDate());
        editExamDescriptor.setTimeslot(exam.getTimeslot());
        if (exam.getLink().isPresent()) {
            editExamDescriptor.setLink(exam.getLink().get());
        }
        if (exam.getVenue().isPresent()) {
            editExamDescriptor.setVenue(exam.getVenue().get());
        }
        EditCommand editExamCommand = (EditExamCommand) parser.parseCommand(EditCommand.COMMAND_WORD
                + " exam " + INDEX_FIRST_EXAM.getOneBased() + " " + PREFIX_CODE + module.getCode() + " "
                + PersonUtil.getEditExamDescriptorDetails(editExamDescriptor), GuiState.DETAILS);
        assertEquals(new EditExamCommand(INDEX_FIRST_EXAM, editExamDescriptor), editExamCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, DEFAULT_STATE) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", DEFAULT_STATE) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")),
                DEFAULT_STATE);
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, DEFAULT_STATE) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", DEFAULT_STATE) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " mod",
                DEFAULT_STATE) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " lesson",
                DEFAULT_STATE) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " exam",
                DEFAULT_STATE) instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand("", DEFAULT_STATE));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand",
                DEFAULT_STATE));
    }
}
