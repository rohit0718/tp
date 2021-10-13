package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GuiState;
import seedu.address.model.Model;

/**
 * Lists all modules in the address book to the user.
 */
public class ListModCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all modules.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(MESSAGE_SUCCESS, false, GuiState.SUMMARY);
    }
}
