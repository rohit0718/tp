package seedu.address.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteModCommand}.
 */
public class DeleteModCommandTest {
    private Model model = new ModelManager(getTypicalModBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module moduleToDelete = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteModCommand(INDEX_FIRST_MODULE);
        String expectedMessage = String.format(DeleteModCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);
        ModelManager expectedModel = new ModelManager(model.getModBook(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        DeleteCommand deleteCommand = new DeleteModCommand(outOfBoundIndex);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module moduleToDelete = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteModCommand(INDEX_FIRST_MODULE);
        String expectedMessage = String.format(DeleteModCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);
        Model expectedModel = new ModelManager(model.getModBook(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);
        showNoModule(expectedModel);
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of ModBook module list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModBook().getModuleList().size());
        DeleteCommand deleteCommand = new DeleteModCommand(outOfBoundIndex);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteModFirstCommand = new DeleteModCommand(INDEX_FIRST_MODULE);
        DeleteCommand deleteModSecondCommand = new DeleteModCommand(INDEX_SECOND_MODULE);

        // same object -> returns true
        assertEquals(deleteModFirstCommand, deleteModFirstCommand);

        // same values -> returns true
        DeleteCommand deleteModFirstCommandCopy = new DeleteModCommand(INDEX_FIRST_MODULE);
        assertEquals(deleteModFirstCommand, deleteModFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(deleteModFirstCommand, 1);

        // null -> returns false
        assertNotEquals(deleteModFirstCommand, null);

        // different module command -> returns false
        assertNotEquals(deleteModFirstCommand, deleteModSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(m -> false);
        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
