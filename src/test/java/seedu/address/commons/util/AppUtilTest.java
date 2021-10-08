package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class AppUtilTest {
    private static final List<Integer> LIST_ONE = List.of(1, 2);
    private static final List<Integer> LIST_TWO = List.of(1, 2, 3);
    private static final List<Integer> LIST_THREE = List.of(2, 3, 4);

    @Test
    public void getImage_exitingImage() {
        assertNotNull(AppUtil.getImage("/images/address_book_32.png"));
    }

    @Test
    public void getImage_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AppUtil.getImage(null));
    }

    @Test
    public void checkArgument_true_nothingHappens() {
        AppUtil.checkArgument(true);
        AppUtil.checkArgument(true, "");
    }

    @Test
    public void checkArgument_falseWithoutErrorMessage_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AppUtil.checkArgument(false));
    }

    @Test
    public void checkArgument_falseWithErrorMessage_throwsIllegalArgumentException() {
        String errorMessage = "error message";
        assertThrows(IllegalArgumentException.class, errorMessage, () -> AppUtil.checkArgument(false, errorMessage));
    }

    @Test
    public void checkAreListsEqual_diffSizeLists_returnsFalse() {
        assertFalse(() -> AppUtil.areListsEqual(LIST_ONE, LIST_TWO));
    }

    @Test
    public void checkAreListsEqual_sameSizeDiffElementsLists_returnsFalse() {
        assertFalse(() -> AppUtil.areListsEqual(LIST_TWO, LIST_THREE));
    }

    @Test
    public void checkAreListsEqual_sameSizeSameElementsLists_returnsTrue() {
        assertTrue(() -> AppUtil.areListsEqual(LIST_TWO, LIST_TWO));
    }
}
