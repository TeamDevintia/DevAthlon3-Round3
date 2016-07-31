package io.github.teamdevintia.round3;


/**
 * @author Shad0wCore
 */
public final class NameTag {

    private String prefix, suffix, identifier;

    public NameTag(String prefix, String suffix, String identifier) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.identifier = identifier;
    }

    public void setPrefix(String newPrefix) {
        this.prefix = newPrefix;
    }

    public void setSuffix(String newSuffix) {
        this.suffix = newSuffix;
    }

    public String prefix() {
        return this.prefix;
    }

    public String suffix() {
        return this.suffix;
    }

    public String identifier() {
        return this.identifier;
    }

}
