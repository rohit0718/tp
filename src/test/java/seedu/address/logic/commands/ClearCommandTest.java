package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.ModBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyModBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, GuiState.SUMMARY);

        assertCommandSuccess(new ClearCommand(), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyModBook_success() {
        Model model = new ModelManager(getTypicalModBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModBook(), new UserPrefs());
        expectedModel.setModBook(new ModBook());
        CommandResult expectedResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, GuiState.SUMMARY);

        assertCommandSuccess(new ClearCommand(), model, expectedResult, expectedModel);
    }

}
