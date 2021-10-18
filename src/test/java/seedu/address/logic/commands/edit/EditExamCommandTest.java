package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.CS2103T_PRACTICAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.edit.EditExamCommand.EditExamDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.exam.Exam;
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
//        Module targetModule = new ModuleBuilder(CS2103T).withExams(CS2103T_PRACTICAL).build();
//
//        Exam editedExam = new ExamBuilder(CS2103T_PRACTICAL).build();
//        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
//        editExamDescriptor.setName(editedExam.getName());
//        editExamDescriptor.setDate(editedExam.getDate());
//        editExamDescriptor.setTimeslot(editedExam.getTimeslot());
//        if (editedExam.getLink().isPresent()) {
//            editExamDescriptor.setLink(editedExam.getLink().get());
//        }
//        if (editedExam.getVenue().isPresent()) {
//            editExamDescriptor.setVenue(editedExam.getVenue().get());
//        }
//        EditCommand editCommand = new EditExamCommand(INDEX_FIRST_EXAM, targetModule.getCode(), editExamDescriptor);
//
//        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam.getName(),
//                targetModule.getCode());
//
//        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getModBook(), new UserPrefs());
//        expectedModel.setExam(targetModule, targetModule.getExams().get(0), editedExam);
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {

    }

    @Test
    public void execute_validIndexFilteredList_success() {

    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {

    }

    @Test
    public void equals() {

    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(m -> false);
        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
