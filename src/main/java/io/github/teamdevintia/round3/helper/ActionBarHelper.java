package io.github.teamdevintia.round3.helper;

import net.minecraft.server.v1_10_R1.Packet;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
public final class ActionBarHelper {

    public static void send(String text) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendPacket(player, preparedActionBarPacket(text));
        }
    }

    public static void sendTo(Player player, String text) {
        sendPacket(player, preparedActionBarPacket(text));
    }

    public static PacketPlayOutChat preparedActionBarPacket(String text) {
        return new PacketPlayOutChat(FormatterHelper.formattedTextMojangSON(text), (byte) 2);
    }

    private static void sendPacket(Player player, Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
