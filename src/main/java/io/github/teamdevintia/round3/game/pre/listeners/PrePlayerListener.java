package io.github.teamdevintia.round3.game.pre.listeners;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.handlers.ListenerHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

/**
 * @author Shad0wCore
 */
public class PrePlayerListener extends ListenerHandler {

    private String joinMessage, quitMessage;
    private Location lobbyLocation;

    public PrePlayerListener(Round3 instance) {
        super(instance);
        this.joinMessage = getInstance().getMessageConstant().get("game.pre.join");
        this.quitMessage = getInstance().getMessageConstant().get("game.pre.quit");
        this.lobbyLocation = getInstance().getLocationConstant().get("game.pre.lobby");
    }

    @EventHandler
    public void playerJoinListener(PlayerJoinEvent event) {
        event.setJoinMessage(this.joinMessage);
        event.getPlayer().teleport(this.lobbyLocation);
    }

    @EventHandler
    public void playerQuitListener(PlayerQuitEvent event) {
        event.setQuitMessage(this.quitMessage);
    }

    @EventHandler
    public void playerDamageListener(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
            if (event.getEntity() instanceof Player) {
                event.getEntity().teleport(this.lobbyLocation);
            }
        }
    }

    @EventHandler
    public void playerDropListener(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void playerPickupListener(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void playerPickup2Listener(PlayerPickupArrowEvent event) {
        event.setCancelled(true);
    }

}
