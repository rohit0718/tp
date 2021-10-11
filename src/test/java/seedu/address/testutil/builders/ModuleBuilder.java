package seedu.address.testutil.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Module objects
 */
public class ModuleBuilder {

    public static final String DEFAULT_CODE = "CS2103T";
    public static final Optional<String> DEFAULT_NAME = Optional.of("Software Engineering");

    private ModuleCode code;
    private Optional<ModuleName> name;
    private List<Lesson> lessons;
    private List<Exam> exams;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     * Note that the default ModuleBuilder will not have any name, lessons nor exams.
     */
    public ModuleBuilder() {
        code = new ModuleCode(DEFAULT_CODE);
        name = Optional.empty();
        lessons = new ArrayList<>();
        exams = new ArrayList<>();
    }

    /**
     * Initialises the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        code = moduleToCopy.getCode();
        name = moduleToCopy.getName();
        lessons = moduleToCopy.getLessons();
        exams = moduleToCopy.getExams();
    }

    /**
     * Adds the default {@code ModuleName} to the {@code Module} that we are building
     */
    public ModuleBuilder withDefaultName() {
        this.name = DEFAULT_NAME.map(ModuleName::new);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(String code) {
        this.code = new ModuleCode(code);
        return this;
    }

    /**
     * Sets the {@code ModuleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.name = Optional.of(new ModuleName(name));
        return this;
    }

    /**
     * Sets the {@code lessons} of the {@code Module} that we are building.
     */
    public ModuleBuilder withLessons(Lesson... lessons) {
        this.lessons = SampleDataUtil.getLessonList(lessons);
        return this;
    }

    /**
     * Sets the {@code exams} of the {@code Module} that we are building.
     */
    public ModuleBuilder withExams(Exam... exams) {
        this.exams = SampleDataUtil.getExamList(exams);
        return this;
    }

    public Module build() {
        return new Module(code, name, lessons, exams);
    }
}
