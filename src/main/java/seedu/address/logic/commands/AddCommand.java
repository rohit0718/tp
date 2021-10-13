package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a person to the address book.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a mod/lesson/exam to the mod book. "
            + "Please specify what you would like to add\n"
            + "Example:\n" + COMMAND_WORD + " mod <parameters>\n"
            + COMMAND_WORD + " lesson <parameters>\n"
            + COMMAND_WORD + " exam <parameters>\n";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
