package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.edit.EditModCommand.EditModDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.ModBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.builders.ModuleBuilder;

public class EditModCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalModBook(), new UserPrefs());

    @Test
    public void execute_allModuleFieldsSpecifiedUnfilteredList_success() {
        Module editedModule = new ModuleBuilder(CS2103T).build();

        EditModDescriptor editModDescriptor = new EditModDescriptor();
        editModDescriptor.setModuleCode(editedModule.getCode());
        if (editedModule.getName().isPresent()) {
            editModDescriptor.setModuleName(editedModule.getName().get());
        }

        EditCommand editCommand = new EditModCommand(INDEX_FIRST_MODULE, editModDescriptor);

        String expectedMessage = String.format(EditModCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule.getCode());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.setModule(editedModule, editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromZeroBased(model.getFilteredModuleList().size() + 1);

        EditCommand editModCommand = new EditModCommand(outOfBoundIndex, new EditModDescriptor());

        assertCommandFailure(editModCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_someModuleFieldsSpecifiedUnfilteredList_success() {
        Module editedModule = new ModuleBuilder(CS2103T).build();

        // Removed Name
        EditModDescriptor editModDescriptor = new EditModDescriptor();
        editModDescriptor.setModuleCode(editedModule.getCode());

        EditCommand editCommand = new EditModCommand(
                INDEX_FIRST_MODULE, editModDescriptor);

        String expectedMessage = String.format(EditModCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule.getCode());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.setModule(editedModule, editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module editedModule = CS2103T.deepCopy();

        EditModDescriptor editModDescriptor = new EditModDescriptor();
        editModDescriptor.setModuleCode(editedModule.getCode());
        if (editedModule.getName().isPresent()) {
            editModDescriptor.setModuleName(editedModule.getName().get());
        }

        EditCommand editModCommand = new EditModCommand(INDEX_FIRST_MODULE, editModDescriptor);
        String expectedMessage = String.format(EditModCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule.getCode());
        Model expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.setModule(editedModule, editedModule);

        assertCommandSuccess(editModCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_throwsCommandException() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        EditModDescriptor editModDescriptor = new EditModDescriptor();
        editModDescriptor.setModuleCode(firstModule.getCode());
        if (firstModule.getName().isPresent()) {
            editModDescriptor.setModuleName(firstModule.getName().get());
        }

        EditCommand editCommand = new EditModCommand(INDEX_SECOND_MODULE, editModDescriptor);

        assertCommandFailure(editCommand, model, EditModCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_throwsCommandException() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        EditModDescriptor editModDescriptor = new EditModDescriptor();
        editModDescriptor.setModuleCode(firstModule.getCode());
        if (firstModule.getName().isPresent()) {
            editModDescriptor.setModuleName(firstModule.getName().get());
        }

        EditCommand editCommand = new EditModCommand(INDEX_SECOND_MODULE, editModDescriptor);

        assertCommandFailure(editCommand, model, EditModCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_throwsCommandException() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Index outOfBoundIndex = Index.fromZeroBased(model.getFilteredModuleList().size() + 1);
        EditModDescriptor editModDescriptor = new EditModDescriptor();
        editModDescriptor.setModuleCode(firstModule.getCode());
        if (firstModule.getName().isPresent()) {
            editModDescriptor.setModuleName(firstModule.getName().get());
        }

        EditCommand editCommand = new EditModCommand(outOfBoundIndex, editModDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Module editedModule = new ModuleBuilder(CS2103T).build();

        EditModDescriptor editModDescriptor = new EditModDescriptor();
        editModDescriptor.setModuleCode(editedModule.getCode());
        if (editedModule.getName().isPresent()) {
            editModDescriptor.setModuleName(editedModule.getName().get());
        }

        EditCommand editModFirstCommand = new EditModCommand(INDEX_FIRST_MODULE, editModDescriptor);
        EditCommand editModSecondCommand = new EditModCommand(INDEX_SECOND_MODULE, editModDescriptor);

        // same object -> returns true
        assertEquals(editModFirstCommand, editModFirstCommand);

        // same values -> returns true
        EditCommand editModFirstCommandCopy = new EditModCommand(INDEX_FIRST_MODULE, editModDescriptor);
        assertEquals(editModFirstCommand, editModFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(editModFirstCommand, new ClearCommand());

        // null -> returns false
        assertNotEquals(null, editModFirstCommand);

        // different index -> returns false
        assertNotEquals(editModFirstCommand, editModSecondCommand);

        // different descriptor -> returns false
        EditModDescriptor editModDescriptorTwo = new EditModDescriptor(editModDescriptor);
        editModDescriptorTwo.setModuleName(new ModuleName("CHAMPBENROCKZ"));
        assertNotEquals(editModFirstCommand, new EditModCommand(INDEX_FIRST_MODULE, editModDescriptorTwo));
    }
}
