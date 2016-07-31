package io.github.teamdevintia.round3.listeners.pre;

import io.github.teamdevintia.round3.ListenerHandler;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.panels.TeamPanel;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Shad0wCore
 */
public class PreInteractListener extends ListenerHandler {

    private TeamPanel teamPanel;

    public PreInteractListener(Round3 instance) {
        super(instance);
        this.teamPanel = (TeamPanel) instance.getPanelManager().getPanel("teamPanel");
    }

    @EventHandler
    public void playerInteractListener(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (event.getMaterial().equals(Material.COMPASS)) {
                event.getPlayer().openInventory(this.teamPanel.panel());
            }
        }
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
