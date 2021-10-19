package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
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
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GuiState;
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
    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalModBook(), new UserPrefs());

    @Test
    public void execute_allExamFieldsSpecifiedUnfilteredList_success() {
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
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, GuiState.DETAILS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.setExam(targetModule, targetModule.getExams().get(0), editedExam);

        assertCommandSuccess(editCommand, model, expectedCommandResult, expectedModel);
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
    public void execute_someExamFieldsSpecifiedUnfilteredList_success() {
        Module targetModule = new ModuleBuilder(CS2103T).withExams(CS2103T_PRACTICAL).build();
        Exam editedExam = new ExamBuilder(CS2103T_PRACTICAL).build();

        // Removed Timeslot and Link Fields
        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
        editExamDescriptor.setName(editedExam.getName());
        editExamDescriptor.setDate(editedExam.getDate());
        if (editedExam.getVenue().isPresent()) {
            editExamDescriptor.setVenue(editedExam.getVenue().get());
        }

        EditCommand editCommand = new EditExamCommand(
                INDEX_FIRST_EXAM, targetModule.getCode(), editExamDescriptor);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam.getName(),
                targetModule.getCode());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, GuiState.DETAILS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.setExam(targetModule, targetModule.getExams().get(0), editedExam);

        assertCommandSuccess(editCommand, model, expectedCommandResult, expectedModel);
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
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, GuiState.DETAILS);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
        expectedModel.setExam(targetModule, examToEdit, editedExam);

        assertCommandSuccess(editExamCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicateExamUnfilteredList_throwsCommandException() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Exam firstExam = firstModule.getExams().get(INDEX_FIRST_EXAM.getZeroBased());

        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
        editExamDescriptor.setName(firstExam.getName());
        editExamDescriptor.setDate(firstExam.getDate());
        editExamDescriptor.setTimeslot(firstExam.getTimeslot());
        if (firstExam.getLink().isPresent()) {
            editExamDescriptor.setLink(firstExam.getLink().get());
        }
        if (firstExam.getVenue().isPresent()) {
            editExamDescriptor.setVenue(firstExam.getVenue().get());
        }

        EditCommand editCommand = new EditExamCommand(INDEX_SECOND_EXAM, firstModule.getCode(), editExamDescriptor);

        assertCommandFailure(editCommand, model, EditExamCommand.MESSAGE_DUPLICATE_EXAM);
    }

    @Test
    public void execute_duplicateExamFilteredList_throwsCommandException() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Exam firstExam = firstModule.getExams().get(INDEX_FIRST_EXAM.getZeroBased());

        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
        editExamDescriptor.setName(firstExam.getName());
        editExamDescriptor.setDate(firstExam.getDate());
        editExamDescriptor.setTimeslot(firstExam.getTimeslot());
        if (firstExam.getLink().isPresent()) {
            editExamDescriptor.setLink(firstExam.getLink().get());
        }
        if (firstExam.getVenue().isPresent()) {
            editExamDescriptor.setVenue(firstExam.getVenue().get());
        }

        EditCommand editCommand = new EditExamCommand(INDEX_SECOND_EXAM, firstModule.getCode(), editExamDescriptor);

        assertCommandFailure(editCommand, model, EditExamCommand.MESSAGE_DUPLICATE_EXAM);
    }

    @Test
    public void execute_invalidExamIndexUnfilteredList_throwsCommandException() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(firstModule.getExams().size() + 1);
        Exam editedExam = new ExamBuilder(CS2103T_PRACTICAL).build();
        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
        editExamDescriptor.setName(editedExam.getName());

        EditCommand editCommand = new EditExamCommand(outOfBoundIndex, firstModule.getCode(), editExamDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
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
        editExamDescriptorTwo.setName(new ExamName("CHAMPBENROCKZ"));
        assertNotEquals(editExamFirstCommand, new EditExamCommand(INDEX_FIRST_EXAM, CS2103T.getCode(),
                editExamDescriptorTwo));
    }
}
