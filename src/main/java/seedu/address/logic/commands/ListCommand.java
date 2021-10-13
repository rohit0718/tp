package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.logic.parser.Type;
import seedu.address.model.Model;

/**
 * Lists all modules / lessons / exams in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all modules / lessons / exams.\n"
            + "Parameters: [mod / lesson / exam]\n"
            + "Example: " + COMMAND_WORD + " mod";

    public static String MESSAGE_SUCCESS_MODULES = "Listed all modules.";
    public static String MESSAGE_SUCCESS_LESSONS = "Listed all lessons.";
    public static String MESSAGE_SUCCESS_EXAMS = "Listed all exams.";

    private final Type type;

    public ListCommand(Type type) {
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        switch (type) {
        case LESSON:
            return new CommandResult(MESSAGE_SUCCESS_LESSONS, false, GuiState.LESSONS);
        case EXAM:
            return new CommandResult(MESSAGE_SUCCESS_EXAMS, false, GuiState.EXAMS);
        }

        return new CommandResult(MESSAGE_SUCCESS_MODULES, false, GuiState.SUMMARY);
    }

}
