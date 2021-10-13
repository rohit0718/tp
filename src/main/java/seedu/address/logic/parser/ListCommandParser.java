package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.list.ListExamCommand;
import seedu.address.logic.commands.list.ListLessonCommand;
import seedu.address.logic.commands.list.ListModCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args, GuiState guiState) throws ParseException {

        if (hasMultipleArguments(args)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        try {
            Type type = ParserUtil.parseFirstArg(args, ListCommand.MESSAGE_USAGE);
            switch (type) {
            case LESSON:
                return new ListLessonCommand();
            case EXAM:
                return new ListExamCommand();
            default:
                return new ListModCommand();
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Checks if an argument string has multiple arguments
     */
    private static boolean hasMultipleArguments(String args) {
        return args.trim().split(" ", 2).length != 1;
    }

}
