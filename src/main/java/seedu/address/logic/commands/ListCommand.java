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

    private final Type type;

    public ListCommand(Type type) {
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        switch (type) {
            case Type.MOD:
                model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
            case Type.LESSON:
                model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
            case Type.EXAM:
                model.updateFilteredExamList(PREDICATE_SHOW_ALL_EXAMS);
        }
        return new CommandResult("Listed all records.");
    }
}
