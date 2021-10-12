package seedu.address.model.module;

import java.util.function.Predicate;

/**
 * Tests that a {@code Module}'s {@code ModuleCode} is the same as the given {@code ModuleCode}
 */
public class ModuleHasModuleCodePredicate implements Predicate<Module> {

    private final ModuleCode code;

    public ModuleHasModuleCodePredicate(ModuleCode code) {
        this.code = code;
    }

    @Override
    public boolean test(Module mod) {
        return mod.getCode().equals(code);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ModuleHasModuleCodePredicate // instanceof handles nulls
            && code.equals(((ModuleHasModuleCodePredicate) other).code)); // state check
    }
}
