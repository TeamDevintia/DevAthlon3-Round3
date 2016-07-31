package io.github.teamdevintia.round3.helper;

import org.bukkit.Location;

import java.util.Objects;

/**
 * @author Shad0wCore
 */
public class LocationHelper {

    public static Location getRandomLocation(Location location1, Location location2) {
        if (!Objects.equals(location1.getWorld().getName(), location2.getWorld().getName())) {
            throw new IllegalArgumentException("Both locations need to be in the same world");
        }

        double minX = location1.getBlockX();
        double minZ = location1.getBlockZ();
        double maxX = location2.getBlockX();
        double maxZ = location2.getBlockZ();

        int ranX = (int) (Math.random() * (maxX - minX) + minX);
        int ranZ = (int) (Math.random() * (maxZ - minZ) + minZ);

        int randomLocHeight = location1.getWorld().getHighestBlockYAt(ranX, ranX);
        return new Location(location1.getWorld(), ranX, randomLocHeight, ranZ);
    }

}
