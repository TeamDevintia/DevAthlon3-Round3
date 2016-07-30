package io.github.teamdevintia.round3.constants;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.handlers.ConstantHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * @author Shad0wCore
 */
public class LocationConstant extends ConstantHandler<Location> {

    public LocationConstant(Round3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.put("game.pre.lobby", new Location(Bukkit.getWorld("world"), 336.5, 107.0, -908.5, 1.5F, 0.5F));
    }

}
