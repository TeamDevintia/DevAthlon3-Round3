package io.github.teamdevintia.round3.handlers;

import io.github.teamdevintia.round3.Round3;
import org.bukkit.event.Listener;

/**
 * @author Shad0wCore
 */
public abstract class ListenerHandler implements Listener {

    private Round3 instance;

    public ListenerHandler(Round3 instance) {
        this.instance = instance;
    }

    public final Round3 getInstance() {
        return this.instance;
    }

}
