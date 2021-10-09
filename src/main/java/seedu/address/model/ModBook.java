package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ReadOnlyModBook;
import seedu.address.model.module.UniqueModuleList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ModBook implements ReadOnlyModBook {
    private final UniqueModuleList modules;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        modules = new UniqueModuleList();
    }

    public ModBook() {
    }

    /**
     * Creates an ModBook using the Modules in the {@code toBeCopied}
     */
    public ModBook(ReadOnlyModBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code ModBook} with {@code newData}.
     */
    public void resetData(ReadOnlyModBook newData) {
        requireNonNull(newData);

        setModules(newData.getModuleList());
    }

}
