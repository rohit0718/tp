package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.lesson.Lesson;

public class DeleteLessonCommand extends DeleteCommand {
    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson %s from Module %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a Lesson from a "
            + "specified Module from the Mod book."
            + "\nParameters: Index "
            + PREFIX_CODE + "MOD_CODE"
            + "\nExample: " + COMMAND_WORD + " lesson 1 "
            + PREFIX_CODE + "CS1231S";
    private final Index targetIndex;
    private final ModuleCode targetModuleCode;

    /**
     * Creates an DeleteLessonCommand to delete the Lesson at specified {@code Index} of the specified {@code Module}.
     */
    public DeleteLessonCommand(Index targetIndex, ModuleCode targetModuleCode) {
        requireAllNonNull(targetIndex, targetModuleCode);
        this.targetIndex = targetIndex;
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = model.getModule(targetModuleCode);
        List<Lesson> lessons = module.getLessons();
        if (targetIndex.getZeroBased() >= lessons.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
        Lesson lessonToDelete = lessons.get(targetIndex.getZeroBased());
        model.deleteLesson(module, lessonToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS,
                lessonToDelete.getName(), targetModuleCode));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteLessonCommand)) {
            return false;
        }
        return targetIndex.equals(((DeleteLessonCommand) other).targetIndex)
                && targetModuleCode.equals(((DeleteLessonCommand) other).targetModuleCode);
    }
}
