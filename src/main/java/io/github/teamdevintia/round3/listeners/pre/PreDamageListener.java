package io.github.teamdevintia.round3.listeners.pre;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.ListenerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * @author Shad0wCore
 */
public class PreDamageListener extends ListenerHandler {

    public PreDamageListener(Round3 instance) {
        super(instance);
    }

    @EventHandler
    public void cancelDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

}
