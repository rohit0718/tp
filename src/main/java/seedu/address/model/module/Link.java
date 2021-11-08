package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a link in the ModBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidLink(String)}
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS = "A link should not be empty and should minimally take the form"
            + " {website name}.{website domain}.\nUse of 'www' and 'http/https' tags are optional.\n"
            + "For example, 'youtube.com' and 'https://youtube.com/help' are valid links.";

    //@@author shezadhassan22-reused
    //Reused from https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url
    public static final String VALIDATION_REGEX = "(http(s)?:\\/\\/.)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\."
            + "[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
    //@@author

    public final String link;

    /**
     * Constructs a {@code Link}.
     *
     * @param link A link.
     */
    public Link(String link) {
        requireNonNull(link);
        checkArgument(isValidLink(link), MESSAGE_CONSTRAINTS);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    /**
     * Returns true if a given string is a valid link.
     */
    public static boolean isValidLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.link;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && link.equals(((Link) other).link)); // state check
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }
}
