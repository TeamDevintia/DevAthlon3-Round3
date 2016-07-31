package io.github.teamdevintia.round3.managers;

import io.github.teamdevintia.round3.PanelUnit;
import io.github.teamdevintia.round3.ListenerHandler;
import io.github.teamdevintia.round3.Round3;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;

/**
 * @author Shad0wCore
 */
public final class PanelManager extends ListenerHandler {

    private final HashMap<String, PanelUnit> bufferedPanels = new HashMap<>();
    private String keySequence;

    public PanelManager(Round3 instance) {
        super(instance);
    }

    public void bindNewPanel(String keySequence, PanelUnit panelUnit) {
        panelUnit.initialization();
        this.bufferedPanels.put(keySequence, panelUnit);
    }

    public void unbindPanel(String keySequence) {
        this.bufferedPanels.remove(keySequence);
    }

    public PanelUnit getPanel(String keySequence) {
        return this.bufferedPanels.get(keySequence);
    }

    @EventHandler
    public void playerClicksPanelItem(InventoryClickEvent event) {
        if (event.getInventory().getName() != null && event.getCurrentItem() != null) {
            bufferedPanels.values().stream().filter(abstractMenu -> event.getInventory().getName().equalsIgnoreCase(abstractMenu.getName())).forEach(abstractMenu -> {
                abstractMenu.triggeredClick(event.getCurrentItem(), event.getSlot(), (Player) event.getWhoClicked());
                event.setCancelled(true);
            });
        }
    }

    @EventHandler
    public void playerEscapesPanelItem(InventoryCloseEvent event) {
        if (event.getInventory().getName() != null) {
            bufferedPanels.values().stream().filter(abstractMenu -> event.getInventory().getName()
                    .equalsIgnoreCase(abstractMenu.getName())).forEach(abstractMenu -> abstractMenu.triggeredEscape(event));
        }
    }

}
