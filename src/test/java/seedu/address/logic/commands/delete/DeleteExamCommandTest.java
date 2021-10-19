package seedu.address.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXAM;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.exam.Exam;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteExamCommand}.
 */
public class DeleteExamCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalModBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        // using a CS2103T clone for tests to prevent double deletions (both by command and tests)
        Module targetModule = CS2103T.deepCopy();
        Exam examToDelete = targetModule.getExams().get(INDEX_FIRST_EXAM.getZeroBased());
        DeleteCommand deleteExamCommand = new DeleteExamCommand(INDEX_FIRST_EXAM);
        String expectedMessage = String.format(DeleteExamCommand.MESSAGE_DELETE_EXAM_SUCCESS,
                examToDelete.getName(), targetModule.getCode());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.deleteExam(targetModule, examToDelete);
        assertCommandSuccess(deleteExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Module targetModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Index outOfBoundIndex = Index.fromZeroBased(targetModule.getExams().size() + 1);
        DeleteCommand deleteExamCommand = new DeleteExamCommand(outOfBoundIndex);
        assertCommandFailure(deleteExamCommand, model, Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module targetModule = CS2103T.deepCopy();
        Exam examToDelete = targetModule.getExams().get(INDEX_FIRST_EXAM.getZeroBased());
        DeleteCommand deleteExamCommand = new DeleteExamCommand(INDEX_FIRST_EXAM);
        String expectedMessage = String.format(DeleteExamCommand.MESSAGE_DELETE_EXAM_SUCCESS, examToDelete.getName(),
                targetModule.getCode());
        Model expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.deleteExam(targetModule, examToDelete);
        showNoModule(expectedModel);
        assertCommandSuccess(deleteExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module targetModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Index outOfBoundIndex = Index.fromZeroBased(targetModule.getExams().size() + 1);
        DeleteCommand deleteExamCommand = new DeleteExamCommand(outOfBoundIndex);
        assertCommandFailure(deleteExamCommand, model, Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteExamFirstCommand = new DeleteExamCommand(INDEX_FIRST_EXAM);
        DeleteCommand deleteExamSecondCommand = new DeleteExamCommand(INDEX_SECOND_EXAM);

        // same object -> returns true
        assertEquals(deleteExamFirstCommand, deleteExamFirstCommand);

        // same values -> returns true
        DeleteCommand deleteExamFirstCommandCopy = new DeleteExamCommand(INDEX_FIRST_EXAM);
        assertEquals(deleteExamFirstCommand, deleteExamFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(deleteExamFirstCommand, 1);

        // null -> returns false
        assertNotEquals(deleteExamFirstCommand, null);

        // different module command -> returns false
        assertNotEquals(deleteExamFirstCommand, deleteExamSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(m -> false);
        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
