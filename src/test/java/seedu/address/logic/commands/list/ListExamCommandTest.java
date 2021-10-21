package seedu.address.logic.commands.list;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GuiState;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListExamCommand.
 */
public class ListExamCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalModBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListExamCommand(), model,
                new CommandResult(ListExamCommand.MESSAGE_SUCCESS, false, GuiState.EXAMS),
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        assertCommandSuccess(new ListExamCommand(), model,
                new CommandResult(ListExamCommand.MESSAGE_SUCCESS, false, GuiState.EXAMS),
                expectedModel);
    }
}
