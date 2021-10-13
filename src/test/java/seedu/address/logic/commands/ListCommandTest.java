package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalModBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalModBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(Type.MOD), model,
                new CommandResult(ListCommand.MESSAGE_SUCCESS_MODULES, false, GuiState.SUMMARY),
                expectedModel);
        assertCommandSuccess(new ListCommand(Type.LESSON), model,
                new CommandResult(ListCommand.MESSAGE_SUCCESS_LESSONS, false, GuiState.LESSONS),
                expectedModel);
        assertCommandSuccess(new ListCommand(Type.EXAM), model,
                new CommandResult(ListCommand.MESSAGE_SUCCESS_EXAMS, false, GuiState.EXAMS),
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        assertCommandSuccess(new ListCommand(Type.MOD), model,
                new CommandResult(ListCommand.MESSAGE_SUCCESS_MODULES, false, GuiState.SUMMARY),
                expectedModel);
        assertCommandSuccess(new ListCommand(Type.LESSON), model,
                new CommandResult(ListCommand.MESSAGE_SUCCESS_LESSONS, false, GuiState.LESSONS),
                expectedModel);
        assertCommandSuccess(new ListCommand(Type.EXAM), model,
                new CommandResult(ListCommand.MESSAGE_SUCCESS_EXAMS, false, GuiState.EXAMS),
                expectedModel);
    }
}
