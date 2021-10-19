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

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GuiState;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Link;
import seedu.address.model.module.ModBookDate;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.Timeslot;
import seedu.address.model.module.Venue;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.exam.ExamName;

public class EditExamCommand extends EditCommand {
    public static final String MESSAGE_DUPLICATE_EXAM = "This exam already exists.";
    public static final String MESSAGE_EDIT_EXAM_SUCCESS = "Edited Exam %s in Module %s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an exam in the Mod book. "
            + "\nParameters: INDEX "
            + PREFIX_CODE + "MOD_CODE "
            + PREFIX_NAME + "EXAM_NAME "
            + PREFIX_DAY + "DAY "
            + PREFIX_START + "START_TIME "
            + PREFIX_END + "END_TIME "
            + PREFIX_LINK + "LINK "
            + PREFIX_VENUE + "VENUE "
            + "\nExample: " + COMMAND_WORD + " exam 1 "
            + PREFIX_CODE + "CS2103 "
            + PREFIX_NAME + "Final "
            + PREFIX_DAY + "02/02/1999 "
            + PREFIX_START + "10:00 "
            + PREFIX_END + "11:00 "
            + PREFIX_LINK + "https://www.youtube.com/watch?v=8mL3L9hN2l4 "
            + PREFIX_VENUE + "Field";
    private final Index targetIndex;
    private final ModuleCode targetModuleCode;
    private final EditExamDescriptor editExamDescriptor;

    /**
     * Creates an EditExamCommand to edit the Exam at specified {@code Index} of the specified {@code Module}.
     */
    public EditExamCommand(Index targetIndex, ModuleCode targetModuleCode, EditExamDescriptor editExamDescriptor) {
        requireAllNonNull(targetIndex, targetModuleCode, editExamDescriptor);

        this.targetIndex = targetIndex;
        this.targetModuleCode = targetModuleCode;
        this.editExamDescriptor = new EditExamDescriptor(editExamDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = model.getModule(targetModuleCode);
        List<Exam> exams = module.getExams();

        if (targetIndex.getZeroBased() >= exams.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }

        Exam examToEdit = exams.get(targetIndex.getZeroBased());
        Exam editedExam = createEditedExam(examToEdit, editExamDescriptor);

        if (!examToEdit.isSameExam(editedExam) && model.moduleHasExam(module, editedExam)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXAM);
        }

        model.setExam(module, examToEdit, editedExam);
        return new CommandResult(String.format(MESSAGE_EDIT_EXAM_SUCCESS, editedExam.getName(),
                module.getCode()), false, GuiState.DETAILS);
    }

    /**
     * Creates and returns a {@code Exam} with the details of {@code examToEdit}
     * edited with {@code editExamDescriptor}.
     */
    private static Exam createEditedExam(Exam examToEdit, EditExamDescriptor editExamDescriptor) {
        assert examToEdit != null;

        ExamName updatedName = editExamDescriptor.getName().orElse(examToEdit.getName());
        ModBookDate updatedDate = editExamDescriptor.getDate().orElse(examToEdit.getDate());
        Timeslot updatedTimeslot = editExamDescriptor.getTimeslot().orElse(examToEdit.getTimeslot());
        Optional<Venue> updatedVenue = Optional.empty();
        if (examToEdit.getVenue().isPresent()) {
            updatedVenue = Optional.of(examToEdit.getVenue().get());
        }
        if (editExamDescriptor.getVenue().isPresent()) {
            updatedVenue = Optional.of(editExamDescriptor.getVenue().get());
        }

        Optional<Link> updatedLink = Optional.empty();
        if (examToEdit.getLink().isPresent()) {
            updatedLink = Optional.of(examToEdit.getLink().get());
        }
        if (editExamDescriptor.getLink().isPresent()) {
            updatedLink = Optional.of(editExamDescriptor.getLink().get());
        }

        return new Exam(updatedName, updatedDate, updatedTimeslot, updatedVenue, updatedLink);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EditExamCommand)) {
            return false;
        }
        return targetIndex.equals(((EditExamCommand) other).targetIndex)
                && targetModuleCode.equals(((EditExamCommand) other).targetModuleCode)
                && editExamDescriptor.equals(((EditExamCommand) other).editExamDescriptor);
    }

    /**
     * Stores the details to edit the exam with. Each non-empty field value will replace the
     * corresponding field value of the exam.
     */
    public static class EditExamDescriptor {
        private ExamName name;
        private ModBookDate date;
        private Timeslot timeslot;
        private Venue venue;
        private Link link;

        public EditExamDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditExamDescriptor(EditExamDescriptor toCopy) {
            setName(toCopy.name);
            setDate(toCopy.date);
            setTimeslot(toCopy.timeslot);
            setVenue(toCopy.venue);
            setLink(toCopy.link);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, date, timeslot, venue, link);
        }

        public void setName(ExamName name) {
            this.name = name;
        }

        public Optional<ExamName> getName() {
            return Optional.ofNullable(name);
        }

        public void setDate(ModBookDate date) {
            this.date = date;
        }

        public Optional<ModBookDate> getDate() {
            return Optional.ofNullable(date);
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
            if (!(other instanceof EditExamDescriptor)) {
                return false;
            }

            // state check
            EditExamDescriptor e = (EditExamDescriptor) other;

            return getName().equals(e.getName())
                    && getDate().equals(e.getDate())
                    && getTimeslot().equals(e.getTimeslot())
                    && getVenue().equals(e.getVenue())
                    && getLink().equals(e.getLink());
        }
    }
}
