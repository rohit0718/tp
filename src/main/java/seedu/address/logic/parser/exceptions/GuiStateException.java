package seedu.address.logic.parser.exceptions;

import seedu.address.logic.commands.DetailCommand;
import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.list.ListCommand;

public class GuiStateException extends ParseException {

    public static final String ERROR_MESSAGE = "This command cannot be used in this screen!";
    public static final String SWITCH_VIEW_MESSAGE = "Please execute the %s command first!";

    public GuiStateException() {
        super(ERROR_MESSAGE);
    }

    public GuiStateException(GuiState guiState) {
        super(fillCommand(guiState));
    }

    public GuiStateException(String message) {
        super(message);
    }

    public GuiStateException(String message, Throwable cause) {
        super(message, cause);
    }

    private static String fillCommand(GuiState guiState) {
        String commandToUse = "";

        switch (guiState) {
        case SUMMARY:
            commandToUse = ListCommand.COMMAND_WORD + " mod";
            break;
        case EXAMS:
            commandToUse = ListCommand.COMMAND_WORD + " exam";
            break;
        case LESSONS:
            commandToUse = ListCommand.COMMAND_WORD + " lesson";
            break;
        case DETAILS:
            commandToUse = DetailCommand.COMMAND_WORD;
            break;
        default:
            // do nothing
        }

        if (commandToUse != "") {
            return String.format(SWITCH_VIEW_MESSAGE, commandToUse);
        }
        return ERROR_MESSAGE;
    }
}
