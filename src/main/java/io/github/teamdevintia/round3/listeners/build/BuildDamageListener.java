package io.github.teamdevintia.round3.listeners.build;

import io.github.teamdevintia.round3.ListenerHandler;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.task.tasks.GameBuildStartTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author Shad0wCore
 */
public class BuildDamageListener extends ListenerHandler {

    private GameBuildStartTask buildStartTask;

    public BuildDamageListener(Round3 instance) {
        super(instance);
    }

    @EventHandler
    public void damageListener(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

}
