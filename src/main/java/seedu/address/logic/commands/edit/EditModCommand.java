package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.lesson.Lesson;

public class EditModCommand extends EditCommand {
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists.";
    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module %s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a module in the Mod book. "
            + "\nParameters: "
            + PREFIX_CODE + "CODE "
            + PREFIX_NAME + "NAME "
            + "\nExample: " + COMMAND_WORD + " mod "
            + PREFIX_CODE + "CS2103 "
            + PREFIX_NAME + "Software Engineering ";
    private final Index targetIndex;
    private final EditModDescriptor editModDescriptor;

    /**
     * Creates an EditModCommand to edit the specified {@code Module}.
     */
    public EditModCommand(Index targetIndex, EditModDescriptor editModDescriptor) {
        requireAllNonNull(targetIndex, editModDescriptor);

        this.targetIndex = targetIndex;
        this.editModDescriptor = new EditModDescriptor(editModDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToEdit = lastShownList.get(targetIndex.getZeroBased());
        Module editedModule = createEditedModule(moduleToEdit, editModDescriptor);

        if (!moduleToEdit.isSameModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code Lesson} with the details of {@code moduleToEdit}
     * edited with {@code editModDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit, EditModDescriptor editModDescriptor) {
        assert moduleToEdit != null;

        ModuleCode updateCode = editModDescriptor.getModuleCode().orElse(moduleToEdit.getCode());

        Optional<ModuleName> updatedName = Optional.empty();
        if (moduleToEdit.getName().isPresent()) {
            updatedName = Optional.of(moduleToEdit.getName().get());
        }
        if (editModDescriptor.getModuleName().isPresent()) {
            updatedName = Optional.of(editModDescriptor.getModuleName().get());
        }

        List<Lesson> lessons = moduleToEdit.getLessons();
        List<Exam> exams = moduleToEdit.getExams();

        return new Module(updateCode, updatedName, lessons, exams);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EditModCommand)) {
            return false;
        }
        return targetIndex.equals(((EditModCommand) other).targetIndex)
                && editModDescriptor.equals(((EditModCommand) other).editModDescriptor);
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModDescriptor {
        private ModuleCode moduleCode;
        private ModuleName moduleName;

        public EditModDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditModDescriptor(EditModDescriptor toCopy) {
            setModuleCode(toCopy.moduleCode);
            setModuleName(toCopy.moduleName);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode, moduleName);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setModuleName(ModuleName moduleName) {
            this.moduleName = moduleName;
        }

        public Optional<ModuleName> getModuleName() {
            return Optional.ofNullable(moduleName);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditModDescriptor)) {
                return false;
            }

            // state check
            EditModDescriptor e = (EditModDescriptor) other;

            return getModuleCode().equals(e.getModuleCode())
                    && getModuleName().equals(e.getModuleName());
        }
    }
}
