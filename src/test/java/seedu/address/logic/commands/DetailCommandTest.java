package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DETAILS_LISTED;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.MA1521;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;
import static seedu.address.testutil.TypicalModules.getTypicalModules;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.predicates.HasModuleCodePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code DetailCommand}
 */
public class DetailCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalModBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalModBook(), new UserPrefs());

    @Test
    public void equals() {
        HasModuleCodePredicate firstPredicate =
                new HasModuleCodePredicate(new ModuleCode("CS2103T"));
        HasModuleCodePredicate secondPredicate =
                new HasModuleCodePredicate(new ModuleCode("CS2101"));

        DetailCommand detailFirstCommand = new DetailCommand(firstPredicate);
        DetailCommand detailSecondCommand = new DetailCommand(secondPredicate);

        // same object -> returns true
        assertTrue(detailFirstCommand.equals(detailFirstCommand));

        // same values -> returns true
        DetailCommand detailFirstCommandCopy = new DetailCommand(firstPredicate);
        assertTrue(detailFirstCommand.equals(detailFirstCommandCopy));

        // different types -> returns false
        assertFalse(detailFirstCommand.equals(1));

        // null -> returns false
        assertFalse(detailFirstCommand.equals(null));

        // different code -> returns false
        assertFalse(detailFirstCommand.equals(detailSecondCommand));
    }

    @Test
    public void execute_validKeyword_moduleFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_DETAILS_LISTED, CS2103T.getCode());
        CommandResult expectedResult = new CommandResult(expectedMessage, false, State.DETAILS);
        HasModuleCodePredicate predicate = preparePredicate(CS2103T.getCode().toString());
        DetailCommand command = new DetailCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(Arrays.asList(CS2103T), model.getFilteredModuleList());
    }

    @Test
    public void execute_moduleNotFound_listAllModules() {
        String expectedMessage = String.format(MESSAGE_MODULE_NOT_FOUND, MA1521.getCode());
        CommandResult expectedResult = new CommandResult(expectedMessage, false, State.SUMMARY);
        HasModuleCodePredicate predicate = preparePredicate(MA1521.getCode().toString());
        DetailCommand command = new DetailCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(getTypicalModules(), model.getFilteredModuleList());
    }

    /**
     * Parses {@code userInput} into a {@code ModuleHasModuleCodePredicate}.
     */
    private HasModuleCodePredicate preparePredicate(String userInput) {
        return new HasModuleCodePredicate(new ModuleCode(userInput));
    }
}
