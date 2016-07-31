package io.github.teamdevintia.round3.schematic;

import io.github.teamdevintia.round3.Round3;
import org.bukkit.block.BlockFace;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author Shad0wCore
 */
public class SchematicWrapper {

    private String spliteratorPrefix = ";";

    public Schematic load(Round3 instance, String schematicName) {
        Schematic loadedSchematic = new Schematic();
        File schematicFile = new File(this.getSchematicDir(instance), schematicName + ".scc");
        loadedSchematic.setSchematicName(schematicName);

        try {
            List<String> rawCodecs = Files.readAllLines(Paths.get(schematicFile.toURI()), Charset.forName("UTF-8"));
            rawCodecs.forEach(rawCodec -> {
                try {
                    String codec = new String(Base64.getDecoder().decode(rawCodec), "UTF-8");
                    String[] raw = codec.split(this.spliteratorPrefix);
                    loadedSchematic.writeCodec(new BlockCodec(Double.parseDouble(raw[0]), Double.parseDouble(raw[1]),
                            Double.parseDouble(raw[2]), Integer.parseInt(raw[3]), Byte.parseByte(raw[4]), BlockFace.valueOf(raw[5])));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedSchematic;
    }

    public boolean save(Round3 instance, Schematic schematic) {
        if (schematic.getSchematicName() == null || schematic.getSchematicName().isEmpty())
            throw new IllegalStateException("The schematic name cannot be null or empty");
        this.createSchematicDir(instance);

        List<String> rawCodecs = new ArrayList<>();

        for (BlockCodec blockCodec : schematic.readCodecs()) {
            String codec = blockCodec.getX() + this.spliteratorPrefix + blockCodec.getY()
                    + this.spliteratorPrefix + blockCodec.getZ() + this.spliteratorPrefix +
                    blockCodec.getID() + this.spliteratorPrefix + blockCodec.getData() + this.spliteratorPrefix + blockCodec.getBlockFace();
            rawCodecs.add(Base64.getEncoder().encodeToString(codec.getBytes()));
        }

        try {
            File schematicFile = new File(this.getSchematicDir(instance), schematic.getSchematicName() + ".scc");
            Files.write(Paths.get(schematicFile.toURI()), rawCodecs, Charset.forName("UTF-8"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private File getSchematicDir(Round3 instance) {
        return new File(instance.getDataFolder(), "sfc");
    }

    private void createSchematicDir(Round3 instance) {
        File schematicDirectory = new File(instance.getDataFolder(), "sfc");
        if (!schematicDirectory.exists())
            schematicDirectory.mkdir();
    }

}
