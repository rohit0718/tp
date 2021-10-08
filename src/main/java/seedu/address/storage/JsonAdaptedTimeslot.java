package seedu.address.storage;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModBookTime;
import seedu.address.model.module.Timeslot;

/**
 * Jackson-friendly version of {@link Timeslot}
 */
public class JsonAdaptedTimeslot {

    private final JsonAdaptedModBookTime startTime;
    private final JsonAdaptedModBookTime endTime;

    /**
     * Constructs a {@code JsonAdaptedTimeslot} with the given start and end times.
     */
    @JsonCreator
    public JsonAdaptedTimeslot(@JsonProperty("startTime") JsonAdaptedModBookTime startTime,
            @JsonProperty("endTime") JsonAdaptedModBookTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Timeslot} into this class for Jackson use.
     */
    public JsonAdaptedTimeslot(Timeslot source) {
        startTime = new JsonAdaptedModBookTime(source.startTime);
        endTime = new JsonAdaptedModBookTime(source.endTime);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Timeslot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Timeslot.
     */
    public Timeslot toModelType() throws IllegalValueException {
        ModBookTime start = startTime.toModelType();
        ModBookTime end = endTime.toModelType();

        if (!Timeslot.isValidTimeslot(start, end)) {
            throw new IllegalValueException(Timeslot.MESSAGE_CONSTRAINTS);
        }
        return new Timeslot(start, end);
    }
}
