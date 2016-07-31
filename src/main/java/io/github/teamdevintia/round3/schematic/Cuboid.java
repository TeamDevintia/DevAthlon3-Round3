package io.github.teamdevintia.round3.schematic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Shad0wCore
 */
public class Cuboid {

    protected Vector minimumPoint, maximumPoint;
    protected String worldName;

    public Cuboid(Cuboid cuboid) {
        this(cuboid.getWorldName(), cuboid.getMinimumPoint().getX(), cuboid.getMinimumPoint().getY(), cuboid.getMinimumPoint().getZ(),
                cuboid.getMaximumPoint().getX(), cuboid.getMaximumPoint().getY(), cuboid.getMaximumPoint().getZ());
    }

    public Cuboid(Location loc1, Location loc2) {
        if (loc1 != null && loc2 != null) {
            if (loc1.getWorld() != null && loc2.getWorld() != null) {
                if (!loc1.getWorld().getUID().equals(loc2.getWorld().getUID()))
                    throw new IllegalStateException("The 2 locations of the cuboid must be in the same world!");
            } else {
                throw new NullPointerException("One/both of the worlds is/are null!");
            }
            this.worldName = loc1.getWorld().getName();

            double xPos1 = Math.min(loc1.getX(), loc2.getX());
            double yPos1 = Math.min(loc1.getY(), loc2.getY());
            double zPos1 = Math.min(loc1.getZ(), loc2.getZ());
            double xPos2 = Math.max(loc1.getX(), loc2.getX());
            double yPos2 = Math.max(loc1.getY(), loc2.getY());
            double zPos2 = Math.max(loc1.getZ(), loc2.getZ());
            this.minimumPoint = new Vector(xPos1, yPos1, zPos1);
            this.maximumPoint = new Vector(xPos2, yPos2, zPos2);
        } else {
            throw new NullPointerException("One/both of the locations is/are null!");
        }
    }

    public Cuboid(String worldName, double x1, double y1, double z1, double x2, double y2, double z2) {
        if (worldName == null || Bukkit.getServer().getWorld(worldName) == null)
            throw new NullPointerException("One/both of the worlds is/are null!");
        this.worldName = worldName;

        double xPos1 = Math.min(x1, x2);
        double xPos2 = Math.max(x1, x2);
        double yPos1 = Math.min(y1, y2);
        double yPos2 = Math.max(y1, y2);
        double zPos1 = Math.min(z1, z2);
        double zPos2 = Math.max(z1, z2);
        this.minimumPoint = new Vector(xPos1, yPos1, zPos1);
        this.maximumPoint = new Vector(xPos2, yPos2, zPos2);
    }

    public boolean containsLocation(Location location) {
        return location != null && location.getWorld().getName().equals(this.worldName) && location.toVector().isInAABB(this.minimumPoint, this.maximumPoint);
    }

    public String getWorldName() {
        return this.worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public Vector getMinimumPoint() {
        return this.minimumPoint;
    }

    public void setMinimumPoint(Vector vector) {
        this.minimumPoint = vector;
    }

    public Vector getMaximumPoint() {
        return this.maximumPoint;
    }

    public void setMaximumPoint(Vector vector) {
        this.maximumPoint = vector;
    }

    public boolean containsVector(Vector vector) {
        return vector != null && vector.isInAABB(this.minimumPoint, this.maximumPoint);
    }

    public ArrayList<Block> getBlocks() {
        List<Block> blockList = new ArrayList<>();
        World world = this.getWorld();
        if (world != null) {
            for (int x = this.minimumPoint.getBlockX(); x <= this.maximumPoint.getBlockX(); x++) {
                for (int y = this.minimumPoint.getBlockY(); y <= this.maximumPoint.getBlockY() && y <= world.getMaxHeight(); y++) {
                    for (int z = this.minimumPoint.getBlockZ(); z <= this.maximumPoint.getBlockZ(); z++) {
                        blockList.add(world.getBlockAt(x, y, z));
                    }
                }
            }
        }
        return (ArrayList<Block>) blockList;
    }

    public Location getLowerLocation() {
        return this.minimumPoint.toLocation(this.getWorld());
    }

    public double getLowerX() {
        return this.minimumPoint.getX();
    }

    public double getLowerY() {
        return this.minimumPoint.getY();
    }

    public double getLowerZ() {
        return this.minimumPoint.getZ();
    }

    public Location getUpperLocation() {
        return this.maximumPoint.toLocation(this.getWorld());
    }

    public double getUpperX() {
        return this.maximumPoint.getX();
    }

    public double getUpperY() {
        return this.maximumPoint.getY();
    }

    public double getUpperZ() {
        return this.maximumPoint.getZ();
    }

    public double getVolume() {
        return (this.getUpperX() - this.getLowerX() + 1) * (this.getUpperY() - this.getLowerY() + 1) * (this.getUpperZ() - this.getLowerZ() + 1);
    }

    public World getWorld() {
        World world = Bukkit.getServer().getWorld(this.worldName);
        if (world == null) throw new NullPointerException("World '" + this.worldName + "' is not loaded.");
        return world;
    }

    public void setWorld(World world) {
        if (world != null) this.worldName = world.getName();
        else throw new NullPointerException("The world cannot be null.");
    }

    @Override
    public Cuboid clone() {
        return new Cuboid(this);
    }

}
