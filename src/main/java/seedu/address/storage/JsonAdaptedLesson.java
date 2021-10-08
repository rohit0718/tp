package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Day;
import seedu.address.model.module.Link;
import seedu.address.model.module.Timeslot;
import seedu.address.model.module.Venue;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.module.lesson.LessonName;

/**
 * Jackson-friendly version of {@link Lesson}
 */
public class JsonAdaptedLesson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";

    private final String name;
    private final String day;
    private final JsonAdaptedTimeslot timeslot;
    private final Optional<String> venue;
    private final Optional<String> link;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("name") String name, @JsonProperty("day") String day,
            @JsonProperty("timeslot") JsonAdaptedTimeslot timeslot, @JsonProperty("venue") Optional<String> venue,
            @JsonProperty("link") Optional<String> link) {
        this.name = name;
        this.day = day;
        this.timeslot = timeslot;
        this.venue = venue;
        this.link = link;
    }

    /**
     * Constructs a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        name = source.getName().fullLessonName;
        day = source.getDay().toString();
        timeslot = new JsonAdaptedTimeslot(source.getTimeslot());
        venue = source.getVenue().map(Venue::toString);
        link = source.getLink().map(Link::toString);
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        final Timeslot modelTimeslot = timeslot.toModelType();
        Optional<Venue> modelVenue = Optional.empty();
        Optional<Link> modelLink = Optional.empty();

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LessonName.class.getSimpleName()));
        }
        if (!LessonName.isValidLessonName(name)) {
            throw new IllegalValueException(LessonName.MESSAGE_CONSTRAINTS);
        }
        final LessonName modelName = new LessonName(name);

        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Day.class.getSimpleName()));
        }
        if (!Day.isValidDay(day)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelDay = Day.valueOf(day.toUpperCase());

        if (venue != null && venue.isPresent()) {
            if (!venue.map(Venue::isValidVenue).orElse(false)) {
                throw new IllegalValueException(Venue.MESSAGE_CONSTRAINTS);
            }
            modelVenue = venue.map(Venue::new);
        }

        if (link != null && link.isPresent()) {
            if (!link.map(Link::isValidLink).orElse(false)) {
                throw new IllegalValueException(Link.MESSAGE_CONSTRAINTS);
            }
            modelLink = venue.map(Link::new);
        }

        return new Lesson(modelName, modelDay, modelTimeslot, modelVenue, modelLink);
    }
}
