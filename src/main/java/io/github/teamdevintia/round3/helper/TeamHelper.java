package io.github.teamdevintia.round3.helper;

import io.github.teamdevintia.round3.NameTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Set;

/**
 * @author Shad0wCore
 */
public final class TeamHelper {

    public static void registerTeam(String identifier) {
        if (!exists(identifier)) {
            Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(identifier);
        }
    }

    public static void registerTeam(NameTag nameTag) {
        if (!exists(nameTag)) {
            registerTeam(nameTag.identifier());
            updateTeam(nameTag);
        }
    }

    public static void deleteTeam(String identifier) {
        if (exists(identifier)) {
            Bukkit.getScoreboardManager().getMainScoreboard().getTeam(identifier).unregister();
        }
    }

    public static void deleteTeam(NameTag nameTag) {
        if (exists(nameTag)) {
            deleteTeam(nameTag.identifier());
        }
    }

    public static void updateTeam(NameTag nameTag) {
        if (exists(nameTag)) {
            Bukkit.getScoreboardManager().getMainScoreboard().getTeam(nameTag.identifier()).setPrefix(nameTag.prefix());
            Bukkit.getScoreboardManager().getMainScoreboard().getTeam(nameTag.identifier()).setSuffix(nameTag.suffix());
        }
    }

    public static boolean exists(NameTag nameTag) {
        return exists(nameTag.identifier());
    }

    public static boolean exists(String identifier) {
        return get(identifier) != null;
    }

    public static boolean contains(String playerName, String identifier) {
        if (exists(identifier)) {
            return get(identifier).hasEntry(playerName);
        }
        return false;
    }

    public static boolean contains(String playerName, NameTag nameTag) {
        return contains(playerName, nameTag.identifier());
    }

    public static boolean contains(Player player, String identifier) {
        if (exists(identifier)) {
            return get(identifier).hasEntry(player.getName());
        }
        return false;
    }

    public static boolean contains(Player player, NameTag nameTag) {
        return contains(player, nameTag.identifier());
    }

    public static void add(Player player, NameTag nameTag) {
        if (exists(nameTag)) {
            get(nameTag).addEntry(player.getName());
        }
    }

    public static void add(Player player, String identifier) {
        if (exists(identifier)) {
            get(identifier).addEntry(player.getName());
        }
    }

    public static Team get(NameTag nameTag) {
        if (exists(nameTag)) {
            return get(nameTag.identifier());
        }
        return null;
    }

    public static Team get(String identifier) {
        if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam(identifier) != null) {
            return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(identifier);
        }
        return null;
    }

    public static Set<Team> getAll() {
        return Bukkit.getScoreboardManager().getMainScoreboard().getTeams();
    }

    public static NameTag getNameTagFrom(Player player) {
        for (Team team : getAll()) {
            if (team.hasEntry(player.getName())) {
                return new NameTag(team.getPrefix(), team.getSuffix(), team.getName());
            }
        }
        return null;
    }

}
