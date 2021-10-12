package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DETAILS_LISTED;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_NOT_FOUND;

import seedu.address.model.Model;
import seedu.address.model.module.ModuleHasModuleCodePredicate;

/**
 * Lists all lessons and exams of a given Module.
 */
public class DetailCommand extends Command {

    public static final String COMMAND_WORD = "detail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all the lessons and exams of a given "
            + "module and displays them with index numbers.\n"
            + "Parameters: c/MODULE_CODE\n"
            + "Example: " + COMMAND_WORD + " c/CS2103T";

    private final ModuleHasModuleCodePredicate predicate;

    public DetailCommand(ModuleHasModuleCodePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        String code = predicate.getCode().toString();

        if (model.getFilteredModuleList().isEmpty()) {
            model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
            return new CommandResult(String.format(MESSAGE_MODULE_NOT_FOUND, code), false, State.SUMMARY);
        }

        return new CommandResult(String.format(MESSAGE_MODULE_DETAILS_LISTED, code), false, State.DETAILS);
    }
}
