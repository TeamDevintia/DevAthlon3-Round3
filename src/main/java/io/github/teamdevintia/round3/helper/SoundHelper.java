package io.github.teamdevintia.round3.helper;

import io.github.teamdevintia.round3.enums.Source;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_10_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_10_R1.CraftSound;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
public final class SoundHelper {

    public static void playCustomSound(Player player, String soundPath, Source source, float volume, float pitch) {
        sendPacket(player, new PacketPlayOutCustomSoundEffect(soundPath, SoundCategory.a(source.source()),
                player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), volume, pitch));
    }

    public static void playCustomSound(Player player, String soundPath, Source source, float volume, float pitch, Location soundLocation) {
        sendPacket(player, new PacketPlayOutCustomSoundEffect(soundPath, SoundCategory.a(source.source()),
                soundLocation.getX(), soundLocation.getY(), soundLocation.getZ(), volume, pitch));
    }

    public static void playSound(Player player, Sound sound, Source source, float volume, float pitch) {
        sendPacket(player, new PacketPlayOutNamedSoundEffect(CraftSound.getSoundEffect(CraftSound.getSound(sound)),
                SoundCategory.a(source.source()), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), volume, pitch));
    }

    public static void playSound(Player player, Sound sound, Source source, float volume, float pitch, Location soundLocation) {
        sendPacket(player, new PacketPlayOutNamedSoundEffect(CraftSound.getSoundEffect(CraftSound.getSound(sound)),
                SoundCategory.a(source.source()), soundLocation.getX(), soundLocation.getY(), soundLocation.getZ(), volume, pitch));
    }

    public static void playCustomSound(String soundPath, Source source, float volume, float pitch) {
        for (Player player : Bukkit.getOnlinePlayers())
            playCustomSound(player, soundPath, source, volume, pitch);
    }

    public static void playCustomSound(String soundPath, Source source, float volume, float pitch, Location soundLocation) {
        for (Player player : Bukkit.getOnlinePlayers())
            playCustomSound(player, soundPath, source, volume, pitch, soundLocation);
    }

    public static void playSound(Sound sound, Source source, float volume, float pitch) {
        for (Player player : Bukkit.getOnlinePlayers())
            playSound(player, sound, source, volume, pitch);
    }

    public static void playSound(Sound sound, Source source, float volume, float pitch, Location soundLocation) {
        for (Player player : Bukkit.getOnlinePlayers())
            playSound(player, sound, source, volume, pitch, soundLocation);
    }

    public static void cancelPlayingSound(Player player, Sound sound, Source source) {
        PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
        packetDataSerializer.a(source.source());
        packetDataSerializer.a(CraftSound.getSound(sound));

        sendPacket(player, new PacketPlayOutCustomPayload("MC|StopSound", packetDataSerializer));
    }

    public static void cancelPlayingSound(Player player, String soundPath, Source source) {
        PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
        packetDataSerializer.a(source.source());
        packetDataSerializer.a(soundPath);

        sendPacket(player, new PacketPlayOutCustomPayload("MC|StopSound", packetDataSerializer));
    }

    public static void cancelPlayingSounds(Player player, Source source) {
        PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
        packetDataSerializer.a(source.source());
        packetDataSerializer.a("");

        sendPacket(player, new PacketPlayOutCustomPayload("MC|StopSound", packetDataSerializer));
    }

    public static void cancelPlayingSound(Sound sound, Source source) {
        for (Player player : Bukkit.getOnlinePlayers())
            cancelPlayingSound(player, sound, source);
    }

    public static void cancelPlayingSound(String soundPath, Source source) {
        for (Player player : Bukkit.getOnlinePlayers())
            cancelPlayingSound(player, soundPath, source);
    }

    public static void cancelPlayingSoundsGlobal(Source source) {
        for (Player player : Bukkit.getOnlinePlayers())
            cancelPlayingSounds(player, source);
    }

    private static void sendPacket(Player player, Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.a().sendPacket(packet);
    }

}
