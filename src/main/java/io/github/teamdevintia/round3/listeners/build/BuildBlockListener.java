package io.github.teamdevintia.round3.listeners.build;

import io.github.teamdevintia.round3.ListenerHandler;
import io.github.teamdevintia.round3.Round3;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Shad0wCore
 */
public class BuildBlockListener extends ListenerHandler {

    public BuildBlockListener(Round3 instance) {
        super(instance);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        switch (event.getBlock().getType()) {
            case IRON_ORE:
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation().add(0.5, 1, 0.5),
                        new ItemStack(Material.IRON_INGOT));
                break;
            case LOG:
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation().add(0.5, 1, 0.5),
                        new ItemStack(Material.LOG));
                break;
        }

        event.setCancelled(true);
    }

}
