package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.edit.EditExamCommand;
import seedu.address.logic.commands.edit.EditLessonCommand;
import seedu.address.logic.commands.edit.EditModCommand;
import seedu.address.model.module.Module;

/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddCommand(Module module) {
        return AddCommand.COMMAND_WORD + " mod " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CODE + module.getCode().toString() + " ");
        if (module.getName().isPresent()) {
            sb.append(PREFIX_NAME + module.getName().toString() + " ");
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModDescriptor}'s details.
     */
    public static String getEditModDescriptorDetails(EditModCommand.EditModDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getModuleName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.moduleName).append(" "));
        descriptor.getModuleCode().ifPresent(code -> sb.append(PREFIX_CODE).append(code.moduleCode).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditLessonDescriptor}'s details.
     */
    public static String getEditLessonDescriptorDetails(EditLessonCommand.EditLessonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullLessonName).append(" "));
        descriptor.getDay().ifPresent(day -> sb.append(PREFIX_DAY).append(day).append(" "));
        descriptor.getTimeslot().ifPresent(timeslot -> sb.append(PREFIX_START).append(timeslot.startTime.toString())
                .append(" ").append(PREFIX_END).append(timeslot.endTime.toString()).append(" "));
        descriptor.getLink().ifPresent(link -> sb.append(PREFIX_LINK).append(link).append(" "));
        descriptor.getVenue().ifPresent(venue -> sb.append(PREFIX_VENUE).append(venue).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExamDescriptor}'s details.
     */
    public static String getEditExamDescriptorDetails(EditExamCommand.EditExamDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullExamName).append(" "));
        descriptor.getDate().ifPresent(day -> sb.append(PREFIX_DAY).append(day).append(" "));
        descriptor.getTimeslot().ifPresent(timeslot -> sb.append(PREFIX_START).append(timeslot.startTime.toString())
                .append(" ").append(PREFIX_END).append(timeslot.endTime.toString()).append(" "));
        descriptor.getLink().ifPresent(link -> sb.append(PREFIX_LINK).append(link).append(" "));
        descriptor.getVenue().ifPresent(venue -> sb.append(PREFIX_VENUE).append(venue).append(" "));
        return sb.toString();
    }
}
