package io.github.teamdevintia.round3;

import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.event.Listener;

/**
 * @author Shad0wCore
 */
public abstract class ListenerHandler implements Listener {

    @Getter(AccessLevel.PUBLIC)
    private final Round3 instance;

    public ListenerHandler(Round3 instance) {
        this.instance = instance;
    }

}
