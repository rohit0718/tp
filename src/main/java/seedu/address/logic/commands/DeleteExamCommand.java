package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exam.Exam;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;

public class DeleteExamCommand extends DeleteCommand {
    private final Index targetIndex;
    private final ModuleCode targetModuleCode;
    public static final String MESSAGE_DELETE_EXAM_SUCCESS = "Deleted Exam %s from Module %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an Exam from a "
            + "specified Module from the Mod book."
            + "\nParameters: Index "
            + PREFIX_CODE + "MOD_CODE"
            + "\nExample: " + COMMAND_WORD + " exam 1 "
            + PREFIX_CODE + "CS2103T";

    /**
     * Creates an DeleteModCommand to delete the Exam at specified {@code Index} of the specified {@code Module}.
     */
    public DeleteExamCommand(Index targetIndex, ModuleCode targetModuleCode) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = model.getModule(targetModuleCode);
        List<Exam> exams = module.getExams();
        if (targetIndex.getZeroBased() >= exams.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }
        Exam examToDelete = exams.get(targetIndex.getZeroBased());
        model.deleteExam(module, examToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EXAM_SUCCESS, examToDelete, targetModuleCode));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteExamCommand)) {
            return false;
        }
        return targetIndex.equals(((DeleteExamCommand) other).targetIndex)
                && targetModuleCode.equals(((DeleteExamCommand) other).targetModuleCode);
    }
}
