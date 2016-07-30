package io.github.teamdevintia.round3.enums;

/**
 * @author Shad0wCore
 */
public enum Source {

    MASTER("master"),
    MUSIC("music"),
    RECORD("record"),
    WEATHER("weather"),
    BLOCK("block"),
    HOSTILE("hostile"),
    NEUTRAL("neutral"),
    PLAYER("player"),
    AMBIENT("ambient"),
    VOICE("voice");

    private String source;

    Source(String source) {
        this.source = source;
    }

    public String source() {
        return this.source;
    }

}
