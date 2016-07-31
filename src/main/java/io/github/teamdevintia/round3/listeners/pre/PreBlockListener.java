package io.github.teamdevintia.round3.listeners.pre;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.ListenerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author Shad0wCore
 */
public class PreBlockListener extends ListenerHandler {

    public PreBlockListener(Round3 instance) {
        super(instance);
    }

    @EventHandler
    public void blockBreakListener(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void blockPlaceListener(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

}
