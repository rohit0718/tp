package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2030S;
import static seedu.address.testutil.TypicalModules.CS2040S;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.testutil.builders.ModBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ModBook(), new ModBook(modelManager.getModBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setModBookFilePath(Paths.get("mod/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setModBookFilePath(Paths.get("mod/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setModBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setModBookFilePath(null));
    }

    @Test
    public void setModBookFilePath_validPath_setsModBookFilePath() {
        Path path = Paths.get("mod/book/file/path");
        modelManager.setModBookFilePath(path);
        assertEquals(path, modelManager.getModBookFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasModule(CS2030S));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addModule(CS2030S);
        assertTrue(modelManager.hasModule(CS2030S));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void equals() {
        ModBook modBook = new ModBookBuilder().withModule(CS2103T).withModule(CS2040S).build();
        ModBook differentModBook = new ModBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(modBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(modBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different modBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentModBook, userPrefs)));

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setModBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(differentModBook, differentUserPrefs)));
    }
}
