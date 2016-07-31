package io.github.teamdevintia.round3.schematic;

import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Shad0wCore
 */
public class Schematic {

    private Set<BlockCodec> codec;
    private String schematicName;

    public Schematic() {
        this.codec = new HashSet<>();
    }

    public void writeCodec(Block block) {
        this.codec.add(new BlockCodec(block.getX(), block.getY(),
                block.getZ(), block.getTypeId(), block.getData(), block.getFace(block)));
    }

    public void writeCodec(BlockCodec blockCodec) {
        this.codec.add(blockCodec);
    }

    public void writeCodecs(List<Block> blocks) {
        blocks.forEach(block -> this.codec.add(new BlockCodec(block.getX(), block.getY(),
                block.getZ(), block.getTypeId(), block.getData(), block.getFace(block))));
    }

    public void writeCodecs(Set<BlockCodec> blockCodecs) {
        this.codec.addAll(blockCodecs);
    }

    public String getSchematicName() {
        return this.schematicName;
    }

    public void setSchematicName(String schematicName) {
        this.schematicName = schematicName;
    }

    public Set<BlockCodec> readCodecs() {
        return this.codec;
    }

    @Override
    public String toString() {
        return "Schematic(codecs=" + codec.size() + ", name=" + schematicName + ")";
    }
}
