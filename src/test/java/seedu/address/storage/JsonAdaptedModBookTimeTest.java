package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModBookTime;

class JsonAdaptedModBookTimeTest {
    private static final String INVALID_TIME = "1200";
    private static final ModBookTime VALID_MODBOOKTIME = new ModBookTime("12:00");

    @Test
    public void toModelType_validTime_returnsModBookTime() throws Exception {
        JsonAdaptedModBookTime time = new JsonAdaptedModBookTime(VALID_MODBOOKTIME);
        assertEquals(VALID_MODBOOKTIME, time.toModelType());
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedModBookTime time = new JsonAdaptedModBookTime(INVALID_TIME);
        String expectedMessage = ModBookTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, time::toModelType);
    }
}
