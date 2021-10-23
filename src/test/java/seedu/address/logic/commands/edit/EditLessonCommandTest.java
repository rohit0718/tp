package seedu.address.logic.commands.edit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.preparePredicate;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.address.testutil.TypicalLessons.CS2103T_LECTURE_WITH_VENUE;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.edit.EditLessonCommand.EditLessonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.module.lesson.LessonName;
import seedu.address.model.module.predicates.HasModuleCodePredicate;
import seedu.address.testutil.builders.LessonBuilder;
import seedu.address.testutil.builders.ModuleBuilder;

public class EditLessonCommandTest {
    private final Model model = new ModelManager(getTypicalModBook(), new UserPrefs());

    @Test
    public void execute_allLessonFieldsSpecifiedUnfilteredList_success() {
        Module targetModule = new ModuleBuilder(CS2103T).withLessons(CS2103T_LECTURE_WITH_VENUE).build();
        Lesson editedLesson = new LessonBuilder(CS2103T_LECTURE_WITH_VENUE).build();

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        editLessonDescriptor.setName(editedLesson.getName());
        editLessonDescriptor.setDay(editedLesson.getDay());
        editLessonDescriptor.setTimeslot(editedLesson.getTimeslot());
        if (editedLesson.getLink().isPresent()) {
            editLessonDescriptor.setLink(editedLesson.getLink().get());
        }
        if (editedLesson.getVenue().isPresent()) {
            editLessonDescriptor.setVenue(editedLesson.getVenue().get());
        }

        EditCommand editCommand = new EditLessonCommand(INDEX_FIRST_LESSON, editLessonDescriptor);

        String expectedMessage = String.format(EditLessonCommand.MESSAGE_EDIT_LESSON_SUCCESS, editedLesson.getName(),
                targetModule.getCode());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);

        ModelManager expectedModel = new ModelManager(model.getModBook(), new UserPrefs());
        expectedModel.setLesson(targetModule, targetModule.getLessons().get(0), editedLesson);

        assertCommandSuccess(editCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Module targetModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Index outOfBoundIndex = Index.fromZeroBased(targetModule.getLessons().size() + 1);

        EditCommand editLessonCommand = new EditLessonCommand(outOfBoundIndex, new EditLessonDescriptor());

        assertCommandFailure(editLessonCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_someLessonFieldsSpecifiedUnfilteredList_success() {
        Module targetModule = new ModuleBuilder(CS2103T).withLessons(CS2103T_LECTURE_WITH_VENUE).build();
        Lesson editedLesson = new LessonBuilder(CS2103T_LECTURE_WITH_VENUE).build();

        // Removed Timeslot and Link Fields
        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        editLessonDescriptor.setName(editedLesson.getName());
        editLessonDescriptor.setDay(editedLesson.getDay());
        if (editedLesson.getVenue().isPresent()) {
            editLessonDescriptor.setVenue(editedLesson.getVenue().get());
        }

        EditCommand editCommand = new EditLessonCommand(INDEX_FIRST_LESSON, editLessonDescriptor);

        String expectedMessage = String.format(EditLessonCommand.MESSAGE_EDIT_LESSON_SUCCESS, editedLesson.getName(),
                targetModule.getCode());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);

        ModelManager expectedModel = new ModelManager(model.getModBook(), new UserPrefs());
        expectedModel.setLesson(targetModule, targetModule.getLessons().get(0), editedLesson);

        assertCommandSuccess(editCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Module targetModule = CS2103T.deepCopy();
        Lesson lessonToEdit = targetModule.getLessons().get(INDEX_FIRST_LESSON.getZeroBased());
        Lesson editedLesson = new LessonBuilder(CS2103T_LECTURE_WITH_VENUE).build();

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        editLessonDescriptor.setName(editedLesson.getName());
        editLessonDescriptor.setDay(editedLesson.getDay());
        editLessonDescriptor.setTimeslot(editedLesson.getTimeslot());
        if (editedLesson.getLink().isPresent()) {
            editLessonDescriptor.setLink(editedLesson.getLink().get());
        }
        if (editedLesson.getVenue().isPresent()) {
            editLessonDescriptor.setVenue(editedLesson.getVenue().get());
        }

        EditCommand editLessonCommand = new EditLessonCommand(INDEX_FIRST_LESSON, editLessonDescriptor);
        String expectedMessage = String.format(EditLessonCommand.MESSAGE_EDIT_LESSON_SUCCESS, lessonToEdit.getName(),
                targetModule.getCode());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);

        Model expectedModel = new ModelManager(model.getModBook(), new UserPrefs());
        expectedModel.setLesson(targetModule, lessonToEdit, editedLesson);
        HasModuleCodePredicate predicate = preparePredicate(CS2103T.getCode().toString());
        expectedModel.updateFilteredModuleList(predicate);

        assertCommandSuccess(editLessonCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicateLessonUnfilteredList_throwsCommandException() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Lesson firstLesson = firstModule.getLessons().get(INDEX_FIRST_LESSON.getZeroBased());

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        editLessonDescriptor.setName(firstLesson.getName());
        editLessonDescriptor.setDay(firstLesson.getDay());
        editLessonDescriptor.setTimeslot(firstLesson.getTimeslot());
        if (firstLesson.getLink().isPresent()) {
            editLessonDescriptor.setLink(firstLesson.getLink().get());
        }
        if (firstLesson.getVenue().isPresent()) {
            editLessonDescriptor.setVenue(firstLesson.getVenue().get());
        }

        EditCommand editCommand = new EditLessonCommand(INDEX_SECOND_LESSON, editLessonDescriptor);

        assertCommandFailure(editCommand, model, EditLessonCommand.MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_duplicateLessonFilteredList_throwsCommandException() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Lesson firstLesson = firstModule.getLessons().get(INDEX_FIRST_LESSON.getZeroBased());

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        editLessonDescriptor.setName(firstLesson.getName());
        editLessonDescriptor.setDay(firstLesson.getDay());
        editLessonDescriptor.setTimeslot(firstLesson.getTimeslot());
        if (firstLesson.getLink().isPresent()) {
            editLessonDescriptor.setLink(firstLesson.getLink().get());
        }
        if (firstLesson.getVenue().isPresent()) {
            editLessonDescriptor.setVenue(firstLesson.getVenue().get());
        }

        EditCommand editCommand = new EditLessonCommand(INDEX_SECOND_LESSON, editLessonDescriptor);

        assertCommandFailure(editCommand, model, EditLessonCommand.MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_invalidLessonIndexUnfilteredList_throwsCommandException() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(firstModule.getLessons().size() + 1);
        Lesson editedLesson = new LessonBuilder(CS2103T_LECTURE_WITH_VENUE).build();
        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        editLessonDescriptor.setName(editedLesson.getName());

        EditCommand editCommand = new EditLessonCommand(outOfBoundIndex, editLessonDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Lesson editedLesson = new LessonBuilder(CS2103T_LECTURE_WITH_VENUE).build();

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();
        editLessonDescriptor.setName(editedLesson.getName());
        editLessonDescriptor.setDay(editedLesson.getDay());
        editLessonDescriptor.setTimeslot(editedLesson.getTimeslot());
        if (editedLesson.getLink().isPresent()) {
            editLessonDescriptor.setLink(editedLesson.getLink().get());
        }
        if (editedLesson.getVenue().isPresent()) {
            editLessonDescriptor.setVenue(editedLesson.getVenue().get());
        }

        EditCommand editLessonFirstCommand = new EditLessonCommand(INDEX_FIRST_LESSON, editLessonDescriptor);
        EditCommand editLessonSecondCommand = new EditLessonCommand(INDEX_SECOND_LESSON, editLessonDescriptor);

        // same object -> returns true
        assertEquals(editLessonFirstCommand, editLessonFirstCommand);

        // same values -> returns true
        EditCommand editLessonFirstCommandCopy = new EditLessonCommand(INDEX_FIRST_LESSON, editLessonDescriptor);
        assertEquals(editLessonFirstCommand, editLessonFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(editLessonFirstCommand, new ClearCommand());

        // null -> returns false
        assertNotEquals(null, editLessonFirstCommand);

        // different index -> returns false
        assertNotEquals(editLessonFirstCommand, editLessonSecondCommand);

        // different descriptor -> returns false
        EditLessonDescriptor editLessonDescriptorTwo = new EditLessonDescriptor(editLessonDescriptor);
        editLessonDescriptorTwo.setName(new LessonName("CHAMPBENROCKZ"));
        assertNotEquals(editLessonFirstCommand, new EditLessonCommand(INDEX_FIRST_LESSON, editLessonDescriptorTwo));
    }
}
