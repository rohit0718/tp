package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.CS2103T_PRACTICAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXAM;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.edit.EditExamCommand.EditExamDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.exam.ExamName;
import seedu.address.testutil.builders.ExamBuilder;
import seedu.address.testutil.builders.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EditExamCommand}.
 */
public class EditExamCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalModBook(), new UserPrefs());

    @Test
    public void constructor_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditExamCommand(null, null, null));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Module targetModule = new ModuleBuilder(CS2103T).withExams(CS2103T_PRACTICAL).build();
        Exam editedExam = new ExamBuilder(CS2103T_PRACTICAL).build();

        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
        editExamDescriptor.setName(editedExam.getName());
        editExamDescriptor.setDate(editedExam.getDate());
        editExamDescriptor.setTimeslot(editedExam.getTimeslot());
        if (editedExam.getLink().isPresent()) {
            editExamDescriptor.setLink(editedExam.getLink().get());
        }
        if (editedExam.getVenue().isPresent()) {
            editExamDescriptor.setVenue(editedExam.getVenue().get());
        }
        EditCommand editCommand = new EditExamCommand(
                INDEX_FIRST_EXAM, targetModule.getCode(), editExamDescriptor);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam.getName(),
                targetModule.getCode());

        ModelManager expectedModel = new ModelManager(
                model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.setExam(targetModule, targetModule.getExams().get(0), editedExam);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Module targetModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Index outOfBoundIndex = Index.fromZeroBased(targetModule.getExams().size() + 1);
        EditCommand editExamCommand = new EditExamCommand(outOfBoundIndex, targetModule.getCode(),
                new EditExamDescriptor());
        assertCommandFailure(editExamCommand, model, Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {

    }


    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module targetModule = CS2103T.deepCopy();
        Exam examToEdit = targetModule.getExams().get(INDEX_FIRST_EXAM.getZeroBased());

        Exam editedExam = new ExamBuilder(CS2103T_PRACTICAL).build();
        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
        editExamDescriptor.setName(editedExam.getName());
        editExamDescriptor.setDate(editedExam.getDate());
        editExamDescriptor.setTimeslot(editedExam.getTimeslot());
        if (editedExam.getLink().isPresent()) {
            editExamDescriptor.setLink(editedExam.getLink().get());
        }
        if (editedExam.getVenue().isPresent()) {
            editExamDescriptor.setVenue(editedExam.getVenue().get());
        }

        EditCommand editExamCommand = new EditExamCommand(INDEX_FIRST_EXAM, targetModule.getCode(),
                editExamDescriptor);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, examToEdit.getName(),
                targetModule.getCode());
        Model expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.setExam(targetModule, examToEdit, editedExam);
        showNoModule(expectedModel);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {

    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {

    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {

    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {

    }

    @Test
    public void equals() {
        Exam editedExam = new ExamBuilder(CS2103T_PRACTICAL).build();

        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
        editExamDescriptor.setName(editedExam.getName());
        editExamDescriptor.setDate(editedExam.getDate());
        editExamDescriptor.setTimeslot(editedExam.getTimeslot());
        if (editedExam.getLink().isPresent()) {
            editExamDescriptor.setLink(editedExam.getLink().get());
        }
        if (editedExam.getVenue().isPresent()) {
            editExamDescriptor.setVenue(editedExam.getVenue().get());
        }

        EditCommand editExamFirstCommand = new EditExamCommand(INDEX_FIRST_EXAM, CS2103T.getCode(),
                editExamDescriptor);
        EditCommand editExamSecondCommand = new EditExamCommand(INDEX_SECOND_EXAM, CS2103T.getCode(),
                editExamDescriptor);

        // same object -> returns true
        assertEquals(editExamFirstCommand, editExamFirstCommand);

        // same values -> returns true
        EditCommand editExamFirstCommandCopy = new EditExamCommand(INDEX_FIRST_EXAM, CS2103T.getCode(),
                editExamDescriptor);
        assertEquals(editExamFirstCommand, editExamFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(editExamFirstCommand, new ClearCommand());

        // null -> returns false
        assertNotEquals(null, editExamFirstCommand);

        // different index -> returns false
        assertNotEquals(editExamFirstCommand, editExamSecondCommand);

        // different descriptor -> returns false
        EditExamDescriptor editExamDescriptorTwo = new EditExamDescriptor(editExamDescriptor);
        editExamDescriptorTwo.setName(new ExamName("random"));
        assertNotEquals(editExamFirstCommand, new EditExamCommand(INDEX_FIRST_EXAM, CS2103T.getCode(),
                editExamDescriptorTwo));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(m -> false);
        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
