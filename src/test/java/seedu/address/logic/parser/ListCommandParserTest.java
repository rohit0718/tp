package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.list.ListExamCommand;
import seedu.address.logic.commands.list.ListLessonCommand;
import seedu.address.logic.commands.list.ListModCommand;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingKeyword_throwsParseException() {
        assertParseFailure(parser, "venue",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraKeyword_throwsParseException() {
        assertParseFailure(parser, "mod lesson",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        // no leading and trailing whitespaces
        ListCommand expectedListModCommand = new ListModCommand();
        assertParseSuccess(parser, " mod", expectedListModCommand);

        ListCommand expectedListLessonCommand = new ListLessonCommand();
        assertParseSuccess(parser, " lesson", expectedListLessonCommand);

        ListCommand expectedListExamCommand = new ListExamCommand();
        assertParseSuccess(parser, " exam", expectedListExamCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " mod" + " \n \t ", expectedListModCommand);

        // parse in various GuiStates
        assertParseSuccess(parser, " mod", GuiState.SUMMARY, expectedListModCommand);
        assertParseSuccess(parser, " mod", GuiState.LESSONS, expectedListModCommand);
        assertParseSuccess(parser, " mod", GuiState.EXAMS, expectedListModCommand);
        assertParseSuccess(parser, " mod", GuiState.DETAILS, expectedListModCommand);
    }
}
