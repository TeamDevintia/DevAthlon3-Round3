package io.github.teamdevintia.round3.game.pre.listeners;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.handlers.ListenerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * @author Shad0wCore
 */
public class PreWorldListener extends ListenerHandler {

    public PreWorldListener(Round3 instance) {
        super(instance);
    }

    @EventHandler
    public void weatherChangeListener(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void creatureSpawnListener(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void entitySpawnListener(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

}
