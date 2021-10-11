package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ModBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyModBook;
import seedu.address.model.module.Day;
import seedu.address.model.module.Link;
import seedu.address.model.module.ModBookDate;
import seedu.address.model.module.ModBookTime;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.Timeslot;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.exam.ExamName;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.module.lesson.LessonName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new ModuleCode("CS1231"), Optional.of(new ModuleName("Discrete Structures"))),
            new Module(new ModuleCode("CS2040S"), Optional.of(new ModuleName("Data Structures and Algorithms"))),
            new Module(new ModuleCode("CS2030S"), Optional.of(new ModuleName("Programming Methodology II"))),
            new Module(new ModuleCode("CS2100"), Optional.of(new ModuleName("Computer Organization"))),
            new Module(new ModuleCode("CS2103T"), Optional.of(new ModuleName("Software Engineering")),
                    Arrays.asList(
                            new Lesson(new LessonName("CS2103T Lecture"), Day.FRIDAY,
                                    new Timeslot(new ModBookTime("16:00"), new ModBookTime("18:00")),
                                    Optional.empty(), Optional.of(new Link("profDamith.com"))),
                            new Lesson(new LessonName("CS2103T Tutorial"), Day.THURSDAY,
                                    new Timeslot(new ModBookTime("13:00"), new ModBookTime("14:00")),
                                    Optional.empty(), Optional.of(new Link("danny.com")))
                    ),
                    Arrays.asList(
                            new Exam(new ExamName("CS2103T Practical"), new ModBookDate("12/11/2021"),
                                    new Timeslot(new ModBookTime("16:00"), new ModBookTime("18:00")),
                                    Optional.empty(), Optional.of(new Link("practical.com"))),
                            new Exam(new ExamName("CS2103T Finals"), new ModBookDate("23/11/2021"),
                                    new Timeslot(new ModBookTime("17:00"), new ModBookTime("19:00")),
                                    Optional.empty(), Optional.of(new Link("final.com")))
                    ))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyModBook getSampleModBook() {
        ModBook sampleMb = new ModBook();
        for (Module sampleModule : getSampleModules()) {
            sampleMb.addModule(sampleModule);
        }
        return sampleMb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a lesson list containing the list of lessons given.
     */
    public static List<Lesson> getLessonList(Lesson... lessons) {
        return Arrays.stream(lessons)
                .collect(Collectors.toList());
    }

    /**
     * Returns an exam list containing the list of exams given.
     */
    public static List<Exam> getExamList(Exam... exams) {
        return Arrays.stream(exams)
                .collect(Collectors.toList());
    }

}
