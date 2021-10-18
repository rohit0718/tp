package seedu.address.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
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
import seedu.address.model.module.lesson.Lesson;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteLessonCommand}.
 */
public class DeleteLessonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalModBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module targetModule = CS2103T.deepCopy();
        Lesson lessonToDelete = targetModule.getLessons().get(INDEX_FIRST_LESSON.getZeroBased());
        DeleteCommand deleteLessonCommand = new DeleteLessonCommand(INDEX_FIRST_LESSON, targetModule.getCode());
        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS,
                lessonToDelete.getName(), targetModule.getCode());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.deleteLesson(targetModule, lessonToDelete);
        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Module targetModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Index outOfBoundIndex = Index.fromZeroBased(targetModule.getLessons().size() + 1);
        DeleteCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundIndex, targetModule.getCode());
        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module targetModule = CS2103T.deepCopy();
        Lesson lessonToDelete = targetModule.getLessons().get(INDEX_FIRST_LESSON.getZeroBased());
        DeleteCommand deleteLessonCommand = new DeleteLessonCommand(INDEX_FIRST_LESSON, targetModule.getCode());
        String expectedMessage = String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS,
                lessonToDelete.getName(), targetModule.getCode());
        Model expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.deleteLesson(targetModule, lessonToDelete);
        assertCommandSuccess(deleteLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module targetModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Index outOfBoundIndex = Index.fromZeroBased(targetModule.getLessons().size() + 1);
        DeleteCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundIndex, targetModule.getCode());
        assertCommandFailure(deleteLessonCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteLessonFirstCommand = new DeleteLessonCommand(INDEX_FIRST_LESSON, CS2103T.getCode());
        DeleteCommand deleteLessonSecondCommand = new DeleteLessonCommand(INDEX_SECOND_LESSON, CS2103T.getCode());

        // same object -> returns true
        assertEquals(deleteLessonFirstCommand, deleteLessonFirstCommand);

        // same values -> returns true
        DeleteCommand deleteLessonFirstCommandCopy = new DeleteLessonCommand(INDEX_FIRST_LESSON, CS2103T.getCode());
        assertEquals(deleteLessonFirstCommand, deleteLessonFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(deleteLessonFirstCommand, 1);

        // null -> returns false
        assertNotEquals(deleteLessonFirstCommand, null);

        // different module command -> returns false
        assertNotEquals(deleteLessonFirstCommand, deleteLessonSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(m -> false);
        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
