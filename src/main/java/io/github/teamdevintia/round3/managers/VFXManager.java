package io.github.teamdevintia.round3.managers;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.VPacket;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
public final class VFXManager {

    public void triggerVFXPacket(Round3 instance, VPacket vPacket, Player to, Location location, Object... optionalArgs) {
        vPacket.play(instance, location, to, optionalArgs);
    }

}
