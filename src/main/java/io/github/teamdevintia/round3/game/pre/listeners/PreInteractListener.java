package io.github.teamdevintia.round3.game.pre.listeners;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.handlers.ListenerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Shad0wCore
 */
public class PreInteractListener extends ListenerHandler {

    public PreInteractListener(Round3 instance) {
        super(instance);
    }

    @EventHandler
    public void playerInteractListener(PlayerInteractEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void playerInteractAtEntityListener(PlayerInteractAtEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void entityInteractListener(EntityInteractEvent event) {
        event.setCancelled(true);
    }

}
