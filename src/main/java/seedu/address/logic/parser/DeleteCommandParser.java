package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.commands.delete.DeleteExamCommand;
import seedu.address.logic.commands.delete.DeleteLessonCommand;
import seedu.address.logic.commands.delete.DeleteModCommand;
import seedu.address.logic.parser.exceptions.GuiStateException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args, GuiState guiState) throws ParseException {
        Type type = ParserUtil.parseFirstArg(args, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
        switch(type) {
        case MOD:
            return parseDeleteMod(args, guiState);
        case LESSON:
            return parseDeleteLesson(args, guiState);
        case EXAM:
            return parseDeleteExam(args, guiState);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteModCommand
     * and returns a DeleteModCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteModCommand parseDeleteMod(String args, GuiState guiState) throws ParseException {
        if (guiState != GuiState.SUMMARY) {
            throw new GuiStateException(GuiState.SUMMARY);
        }

        // defensive coding to guard against users who erroneously add extra prefixes which may cause it to mess up
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DAY,
                PREFIX_START, PREFIX_END, PREFIX_LINK, PREFIX_VENUE);
        Index index = ParserUtil.parseLastIndex(argMultimap.getPreamble());
        return new DeleteModCommand(index);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteLessonCommand
     * and returns a DeleteLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteLessonCommand parseDeleteLesson(String args, GuiState guiState) throws ParseException {
        if (guiState != GuiState.DETAILS) {
            throw new GuiStateException(GuiState.DETAILS);
        }

        // defensive coding to guard against users who erroneously add extra prefixes which may cause it to mess up
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DAY,
                PREFIX_START, PREFIX_END, PREFIX_LINK, PREFIX_VENUE);
        Index index = ParserUtil.parseLastIndex(argMultimap.getPreamble());
        return new DeleteLessonCommand(index);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteExamCommand
     * and returns a DeleteExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteExamCommand parseDeleteExam(String args, GuiState guiState) throws ParseException {
        if (guiState != GuiState.DETAILS) {
            throw new GuiStateException(GuiState.DETAILS);
        }

        // defensive coding to guard against users who erroneously add extra prefixes which may cause it to mess up
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DAY,
                PREFIX_START, PREFIX_END, PREFIX_LINK, PREFIX_VENUE);
        Index index = ParserUtil.parseLastIndex(argMultimap.getPreamble());
        return new DeleteExamCommand(index);
    }
}
