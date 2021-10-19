package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Day;
import seedu.address.model.module.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.Timeslot;
import seedu.address.model.module.Venue;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.module.lesson.LessonName;

public class EditLessonCommand extends EditCommand {
    public static final String MESSAGE_DUPLICATE_LESSON = "This lesson already exists.";
    public static final String MESSAGE_EDIT_LESSON_SUCCESS = "Edited Lesson %s in Module %s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a lesson in the Mod book. "
            + "\nParameters: INDEX "
            + PREFIX_CODE + "MOD_CODE "
            + PREFIX_NAME + "LESSON_NAME "
            + PREFIX_DAY + "DAY "
            + PREFIX_START + "START_TIME "
            + PREFIX_END + "END_TIME "
            + PREFIX_LINK + "LINK "
            + PREFIX_VENUE + "VENUE "
            + "\nExample: " + COMMAND_WORD + " lesson 1 "
            + PREFIX_CODE + "CS2103 "
            + PREFIX_NAME + "Tutorial "
            + PREFIX_DAY + "Monday "
            + PREFIX_START + "10:00 "
            + PREFIX_END + "11:00 "
            + PREFIX_LINK + "https://www.youtube.com/watch?v=8mL3L9hN2l4 "
            + PREFIX_VENUE + "COM1 ";
    private final Index targetIndex;
    private final ModuleCode targetModuleCode;
    private final EditLessonDescriptor editLessonDescriptor;

    /**
     * Creates an EditLessonCommand to edit the Lesson at specified {@code Index} of the specified {@code Module}.
     */
    public EditLessonCommand(Index targetIndex, ModuleCode targetModuleCode,
                             EditLessonDescriptor editLessonDescriptor) {
        requireAllNonNull(targetIndex, targetModuleCode, editLessonDescriptor);

        this.targetIndex = targetIndex;
        this.targetModuleCode = targetModuleCode;
        this.editLessonDescriptor = new EditLessonDescriptor(editLessonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = model.getModule(targetModuleCode);
        List<Lesson> lessons = module.getLessons();

        if (targetIndex.getZeroBased() >= lessons.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToEdit = lessons.get(targetIndex.getZeroBased());
        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);

        if (!lessonToEdit.isSameLesson(editedLesson) && model.moduleHasLesson(module, editedLesson)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        model.setLesson(module, lessonToEdit, editedLesson);
        return new CommandResult(String.format(MESSAGE_EDIT_LESSON_SUCCESS, editedLesson.getName(), module.getCode()));
    }

    /**
     * Creates and returns a {@code Lesson} with the details of {@code lessonToEdit}
     * edited with {@code editLessonDescriptor}.
     */
    private static Lesson createEditedLesson(Lesson lessonToEdit, EditLessonDescriptor editLessonDescriptor) {
        assert lessonToEdit != null;

        LessonName updatedName = editLessonDescriptor.getName().orElse(lessonToEdit.getName());
        Day updatedDay = editLessonDescriptor.getDay().orElse(lessonToEdit.getDay());
        Timeslot updatedTimeslot = editLessonDescriptor.getTimeslot().orElse(lessonToEdit.getTimeslot());

        Optional<Venue> updatedVenue = Optional.empty();
        if (lessonToEdit.getVenue().isPresent()) {
            updatedVenue = Optional.of(lessonToEdit.getVenue().get());
        }
        if (editLessonDescriptor.getVenue().isPresent()) {
            updatedVenue = Optional.of(editLessonDescriptor.getVenue().get());
        }

        Optional<Link> updatedLink = Optional.empty();
        if (lessonToEdit.getLink().isPresent()) {
            updatedLink = Optional.of(lessonToEdit.getLink().get());
        }
        if (editLessonDescriptor.getLink().isPresent()) {
            updatedLink = Optional.of(editLessonDescriptor.getLink().get());
        }

        return new Lesson(updatedName, updatedDay, updatedTimeslot, updatedVenue, updatedLink);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EditLessonCommand)) {
            return false;
        }
        return targetIndex.equals(((EditLessonCommand) other).targetIndex)
                && targetModuleCode.equals(((EditLessonCommand) other).targetModuleCode)
                && editLessonDescriptor.equals(((EditLessonCommand) other).editLessonDescriptor);
    }

    /**
     * Stores the details to edit the lesson with. Each non-empty field value will replace the
     * corresponding field value of the lesson.
     */
    public static class EditLessonDescriptor {
        private LessonName name;
        private Day day;
        private Timeslot timeslot;
        private Venue venue;
        private Link link;

        public EditLessonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditLessonDescriptor(EditLessonDescriptor toCopy) {
            setName(toCopy.name);
            setDay(toCopy.day);
            setTimeslot(toCopy.timeslot);
            setVenue(toCopy.venue);
            setLink(toCopy.link);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, day, timeslot, venue, link);
        }

        public void setName(LessonName name) {
            this.name = name;
        }

        public Optional<LessonName> getName() {
            return Optional.ofNullable(name);
        }

        public void setDay(Day day) {
            this.day = day;
        }

        public Optional<Day> getDay() {
            return Optional.ofNullable(day);
        }

        public void setTimeslot(Timeslot timeslot) {
            this.timeslot = timeslot;
        }

        public Optional<Timeslot> getTimeslot() {
            return Optional.ofNullable(timeslot);
        }

        public void setVenue(Venue venue) {
            this.venue = venue;
        }

        public Optional<Venue> getVenue() {
            return Optional.ofNullable(venue);
        }

        public void setLink(Link link) {
            this.link = link;
        }

        public Optional<Link> getLink() {
            return Optional.ofNullable(link);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditLessonDescriptor)) {
                return false;
            }

            // state check
            EditLessonDescriptor e = (EditLessonDescriptor) other;

            return getName().equals(e.getName())
                    && getDay().equals(e.getDay())
                    && getTimeslot().equals(e.getTimeslot())
                    && getVenue().equals(e.getVenue())
                    && getLink().equals(e.getLink());
        }
    }
}
