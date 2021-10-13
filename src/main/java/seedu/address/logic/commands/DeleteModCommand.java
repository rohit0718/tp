package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteModCommand extends DeleteCommand {
    private final Index targetIndex;
    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a module from the Mod book. "
            + "\nParameters: Index\n"
            + "Example: " + COMMAND_WORD + " module 1";

    /**
     * Creates an DeleteModCommand to delete the module at specified {@code Index}
     */
    public DeleteModCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }
        Module moduleToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteModCommand)) {
            return false;
        }
        return targetIndex.equals(((DeleteModCommand) other).targetIndex);
    }
}
