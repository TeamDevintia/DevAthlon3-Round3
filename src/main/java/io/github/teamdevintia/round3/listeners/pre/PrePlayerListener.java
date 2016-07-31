package io.github.teamdevintia.round3.listeners.pre;

import io.github.teamdevintia.round3.ListenerHandler;
import io.github.teamdevintia.round3.Round3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

/**
 * @author Shad0wCore
 */
public class PrePlayerListener extends ListenerHandler {

    private String prefix, joinMessage, quitMessage;
    private Location lobbyLocation;

    public PrePlayerListener(Round3 instance) {
        super(instance);
        this.prefix = getInstance().getMessageConstant().get("generics.prefix");
        this.joinMessage = getInstance().getMessageConstant().get("generics.lobby.join");
        this.quitMessage = getInstance().getMessageConstant().get("generics.lobby.quit");
        this.lobbyLocation = getInstance().getLocationConstant().get("generics.locations.lobby");
    }

    @EventHandler
    public void playerJoinListener(PlayerJoinEvent event) {
        event.setJoinMessage(this.prefix + "§6" + event.getPlayer().getName() + this.joinMessage);
        event.getPlayer().teleport(this.lobbyLocation);

        for (Player player : Bukkit.getOnlinePlayers()) {
           player.hidePlayer(event.getPlayer());
           player.showPlayer(event.getPlayer());
        }
    }

    @EventHandler
    public void playerQuitListener(PlayerQuitEvent event) {
        event.setQuitMessage(this.prefix + "§6" + event.getPlayer().getName() + this.quitMessage);
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
