package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;

import seedu.address.logic.commands.DetailCommand;
import seedu.address.logic.commands.GuiState;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.predicates.HasModuleCodePredicate;

public class DetailCommandParser implements Parser<DetailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DetailCommand
     * and returns a DetailCommand for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DetailCommand parse(String args, GuiState guiState) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CODE);

        if (!argMultimap.getValue(PREFIX_CODE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailCommand.MESSAGE_USAGE));
        }

        ModuleCode code = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_CODE).get());

        return new DetailCommand(new HasModuleCodePredicate(code));
    }
}
