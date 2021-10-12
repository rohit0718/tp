package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DetailCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.predicates.HasModuleCodePredicate;

public class DetailCommandParserTest {

    private DetailCommandParser parser = new DetailCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingKeyword_throwsParseException() {
        assertParseFailure(parser, "cs", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidModuleCode_throwsParseException() {
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_returnsDetailCommand() {
        // no leading and trailing whitespaces
        DetailCommand expectedDetailCommand =
                new DetailCommand(new HasModuleCodePredicate(new ModuleCode(VALID_MODULE_CODE)));
        assertParseSuccess(parser, VALID_MODULE_CODE_DESC, expectedDetailCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " " + PREFIX_CODE + " \n \t " + VALID_MODULE_CODE
                + " \n \t", expectedDetailCommand);
    }
}
