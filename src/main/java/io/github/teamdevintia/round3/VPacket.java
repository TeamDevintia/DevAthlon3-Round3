package io.github.teamdevintia.round3;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
public interface VPacket {

    void play(Round3 instance, Location location, Player toPlayer, Object... optionalArgs);

}
