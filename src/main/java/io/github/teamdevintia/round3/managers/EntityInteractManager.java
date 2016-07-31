package io.github.teamdevintia.round3.managers;

import io.github.teamdevintia.round3.ListenerHandler;
import io.github.teamdevintia.round3.PanelUnit;
import io.github.teamdevintia.round3.Round3;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Shad0wCore
 */
public final class EntityInteractManager extends ListenerHandler {

    private final HashMap<UUID, PanelUnit> menuHashMap = new HashMap<>();

    public EntityInteractManager(Round3 instance) {
        super(instance);
    }

    public void bindNewListener(Entity entity, PanelUnit panelUnit) {
        this.menuHashMap.putIfAbsent(entity.getUniqueId(), panelUnit);
    }

    public void removeBindedListener(Entity entity) {
        if (this.menuHashMap.get(entity.getUniqueId()) != null) {
            this.menuHashMap.remove(entity.getUniqueId());
        }
    }

    public PanelUnit getMenuFrom(Entity entity) {
        if (this.menuHashMap.get(entity.getUniqueId()) != null) {
            return this.menuHashMap.get(entity.getUniqueId());
        }
        return null;
    }

    @EventHandler(ignoreCancelled = true)
    public void playerClicksEntityItem(PlayerInteractEntityEvent event) {
        if (this.menuHashMap.get(event.getRightClicked().getUniqueId()) != null) {
            event.getPlayer().openInventory(this.menuHashMap.get(event.getRightClicked().getUniqueId()).panel());
        }
    }


}
