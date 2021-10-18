package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Set;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.edit.EditExamCommand;
import seedu.address.logic.commands.edit.EditLessonCommand;
import seedu.address.logic.commands.edit.EditModCommand;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddCommand(Module module) {
        return AddCommand.COMMAND_WORD + " mod " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
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
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
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
