package seedu.address.logic.commands.edit;

import seedu.address.logic.commands.Command;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a mod/lesson/exam in the mod book. "
            + "Please specify what you would like to edit\n"
            + "Example:\n" + COMMAND_WORD + " mod <parameters>\n"
            + COMMAND_WORD + " lesson <parameters>\n"
            + COMMAND_WORD + " exam <parameters>\n";
}
