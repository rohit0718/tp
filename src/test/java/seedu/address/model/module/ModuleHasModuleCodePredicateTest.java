package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.builders.ModuleBuilder;

public class ModuleHasModuleCodePredicateTest {

    private static ModuleCode modCode = new ModuleCode(ModuleBuilder.DEFAULT_CODE);

    @Test
    public void equals() {
        ModuleCode secondModuleCode = new ModuleCode("CS2101");

        ModuleHasModuleCodePredicate firstPredicate = new ModuleHasModuleCodePredicate(modCode);
        ModuleHasModuleCodePredicate secondPredicate = new ModuleHasModuleCodePredicate(secondModuleCode);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleHasModuleCodePredicate firstPredicateCopy = new ModuleHasModuleCodePredicate(modCode);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different module code -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleContainsModuleCode_returnsTrue() {
        ModuleHasModuleCodePredicate predicate = new ModuleHasModuleCodePredicate(modCode);
        assertTrue(predicate.test(new ModuleBuilder().build()));
    }

    @Test
    public void test_moduleDoesNotContainModuleCode_returnsFalse() {
        ModuleHasModuleCodePredicate predicate = new ModuleHasModuleCodePredicate(modCode);
        assertFalse(predicate.test(new ModuleBuilder().withCode("CS1101S").build()));
    }
}
