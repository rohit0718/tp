package seedu.address.logic.parser.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.exceptions.GuiStateException.SWITCH_VIEW_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DetailCommand;
import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.list.ListCommand;

class GuiStateExceptionTest {

    @Test
    public void constructor_validGuiState_success() {
        assertEquals(new GuiStateException(GuiState.SUMMARY).getMessage(),
                String.format(SWITCH_VIEW_MESSAGE, ListCommand.COMMAND_WORD + " mod"));
        assertEquals(new GuiStateException(GuiState.LESSONS).getMessage(),
                String.format(SWITCH_VIEW_MESSAGE, ListCommand.COMMAND_WORD + " lesson"));
        assertEquals(new GuiStateException(GuiState.EXAMS).getMessage(),
                String.format(SWITCH_VIEW_MESSAGE, ListCommand.COMMAND_WORD + " exam"));
        assertEquals(new GuiStateException(GuiState.DETAILS).getMessage(),
                String.format(SWITCH_VIEW_MESSAGE, DetailCommand.COMMAND_WORD));
    }

    @Test
    public void constructor_invalidGuiState_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new GuiStateException(GuiState.EXIT));
    }

    @Test
    public void constructor_nullGuiState_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GuiStateException((GuiState) null));
    }
}
