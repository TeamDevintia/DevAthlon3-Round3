package io.github.teamdevintia.round3.constants;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.ConstantHandler;
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
        this.put("generics.locations.lobby", new Location(Bukkit.getWorld("lobby"), 336.5, 107.0, -908.5, 1.5F, 0.5F));
        this.put("generics.locations.game", new Location(Bukkit.getWorld("game"), -42, 56, -26));
        this.put("generics.locations.teamBlueScatter1", new Location(Bukkit.getWorld("game"), -55, 57, -487));
        this.put("generics.locations.teamBlueScatter2", new Location(Bukkit.getWorld("game"), -18, 55, -469));
        this.put("generics.locations.teamRedScatter1", new Location(Bukkit.getWorld("game"), -136, 57, -351));
        this.put("generics.locations.teamRedScatter2", new Location(Bukkit.getWorld("game"), -122, 57, -346));
    }

}
