package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.exam.Exam;

public class DeleteExamCommand extends DeleteCommand {
    public static final String MESSAGE_DELETE_EXAM_SUCCESS = "Deleted Exam %s from Module %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an Exam from a "
            + "specified Module from the Mod book."
            + "\nParameters: Index "
            + "\nExample: " + COMMAND_WORD + " exam 1 ";
    private final Index targetIndex;

    /**
     * Creates an DeleteExamCommand to delete the Exam at specified {@code Index} of the specified {@code Module}.
     */
    public DeleteExamCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = model.getFilteredModuleList().get(0);
        List<Exam> exams = module.getExams();
        if (targetIndex.getZeroBased() >= exams.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }
        Exam examToDelete = exams.get(targetIndex.getZeroBased());
        model.deleteExam(module, examToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXAM_SUCCESS, examToDelete.getName(), module.getCode()));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteExamCommand)) {
            return false;
        }
        return targetIndex.equals(((DeleteExamCommand) other).targetIndex);
    }
}
