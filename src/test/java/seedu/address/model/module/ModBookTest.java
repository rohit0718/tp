package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME;
import static seedu.address.testutil.TypicalModules.CS2040S;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ModBook;
import seedu.address.model.ReadOnlyModBook;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.testutil.builders.ModuleBuilder;

public class ModBookTest {

    private final ModBook modBook = new ModBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), modBook.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyModBook_replacesData() {
        ModBook newData = getTypicalModBook();
        modBook.resetData(newData);
        assertEquals(newData, modBook);
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module editedCS2040S = new ModuleBuilder(CS2040S).withName(VALID_MODULE_NAME).build();
        List<Module> newModules = Arrays.asList(CS2040S, editedCS2040S);
        ModBookStub newData = new ModBookStub(newModules);

        assertThrows(DuplicateModuleException.class, () -> modBook.resetData(newData));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modBook.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInModBook_returnsFalse() {
        assertFalse(modBook.hasModule(CS2103T));
    }

    @Test
    public void hasModule_moduleInModBook_returnsTrue() {
        modBook.addModule(CS2103T);
        assertTrue(modBook.hasModule(CS2103T));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInModBook_returnsTrue() {
        modBook.addModule(CS2040S);
        Module editedCS2040S = new ModuleBuilder(CS2040S).withName(VALID_MODULE_NAME).build();
        assertTrue(modBook.hasModule(editedCS2040S));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modBook.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyModBook whose module list can violate interface constraints.
     */
    private static class ModBookStub implements ReadOnlyModBook {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        ModBookStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }
}
