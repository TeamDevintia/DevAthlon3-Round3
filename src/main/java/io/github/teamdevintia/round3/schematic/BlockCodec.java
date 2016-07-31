package io.github.teamdevintia.round3.schematic;

import org.bukkit.block.BlockFace;

/**
 * @author Shad0wCore
 */
public class BlockCodec {

    private double x, y, z;
    private int id;
    private byte data;
    private BlockFace blockFace;

    public BlockCodec() {
        this(0, 0, 0, 1, (byte) 0, BlockFace.SELF);
    }

    public BlockCodec(double x, double y, double z, int id, byte data, BlockFace blockFace) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
        this.data = data;
        this.blockFace = blockFace;
    }

    public int getX() {
        return (int) this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getY() {
        return (int) this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getZ() {
        return (int) this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public byte getData() {
        return this.data;
    }

    public void setData(byte data) {
        this.data = data;
    }

    public BlockFace getBlockFace() {
        return this.blockFace;
    }

    public void setBlockFace(BlockFace blockFace) {
        this.blockFace = blockFace;
    }

    @Override
    public String toString() {
        return "BlockCodec(x=" + x + ",y=" + y + ",z=" + z + ",blockID=" + id + ",blockData= " + data + ",blockFace=" + blockFace + ")";
    }
}
