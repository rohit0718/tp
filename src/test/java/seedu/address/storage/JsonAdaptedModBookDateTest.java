package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModBookDate;

public class JsonAdaptedModBookDateTest {
    private static final String INVALID_DATE = "12 112021";
    private static final ModBookDate VALID_MODBOOKDATE = new ModBookDate("09/09/2021");

    @Test
    public void toModelType_validDate_returnsModBookDate() throws Exception {
        JsonAdaptedModBookDate date = new JsonAdaptedModBookDate(VALID_MODBOOKDATE);
        assertEquals(VALID_MODBOOKDATE, date.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedModBookDate date = new JsonAdaptedModBookDate(INVALID_DATE);
        String expectedMessage = ModBookDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, date::toModelType);
    }
}
