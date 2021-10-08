package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.AppUtil.areListsEqual;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.lesson.Lesson;

/**
 * Represents a Module in the ModBook.
 * Guarantees: immutable.
 */
public class Module {
    private final ModuleCode moduleCode;
    private final Optional<ModuleName> moduleName;
    private final List<Lesson> lessons;
    private final List<Exam> exams;

    /**
     * Constructs a {@code Module}
     *
     * @param moduleCode Code of module
     * @param moduleName Optional name of Module
     */
    public Module(ModuleCode moduleCode, Optional<ModuleName> moduleName) {
        requireAllNonNull(moduleCode, moduleName);
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.lessons = new ArrayList<>();
        this.exams = new ArrayList<>();
    }

    public ModuleCode getCode() {
        return moduleCode;
    }

    public Optional<ModuleName> getName() {
        return moduleName;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Exam> getExams() {
        return exams;
    }

    /**
     * Returns true if both modules have the same code.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        return otherModule != null && moduleCode.equals(otherModule.getCode());
    }

    /**
     * Returns true if both modules have the same identity and attributes.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Module)) {
            return false;
        }
        Module otherModule = (Module) other;
        return isSameModule(otherModule)
                && moduleName.equals(otherModule.getName())
                && areListsEqual(lessons, otherModule.getLessons())
                && areListsEqual(exams, otherModule.getExams());
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode, moduleName, lessons, exams);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCode());
        moduleName.ifPresent(name -> builder.append(String.format("; Name: %s", name)));
        if (!lessons.isEmpty()) {
            builder.append("; Lessons: ");
            lessons.forEach(lesson -> builder.append(String.format("[%s]", lesson)));
        }
        if (!exams.isEmpty()) {
            builder.append("; Exams: ");
            exams.forEach(exam -> builder.append(String.format("[%s]", exam)));
        }
        return builder.toString();
    }
}
