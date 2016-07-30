package io.github.teamdevintia.round3.task.tasks;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.enums.Source;
import io.github.teamdevintia.round3.exceptions.KernelException;
import io.github.teamdevintia.round3.game.manifest.CommandDebug;
import io.github.teamdevintia.round3.helper.ActionBarHelper;
import io.github.teamdevintia.round3.helper.FormatterHelper;
import io.github.teamdevintia.round3.helper.SoundHelper;
import io.github.teamdevintia.round3.task.Task;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * @author Shad0wCore
 */
public class LobbyTask extends Task {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private int currentCountdownPosition, countdown, startNotifier, minPlayers;
    @Getter(AccessLevel.PUBLIC)
    private String prefix, countdownMessage;

    private Location lobbyLocation;

    public LobbyTask(Round3 instance) {
        super(instance);
        this.countdown = Integer.parseInt(pluginInstance.getPropertyConstant().get("generic.lobby.countdown"));
        this.currentCountdownPosition = this.countdown;
        this.startNotifier = Integer.parseInt(pluginInstance.getPropertyConstant().get("generic.lobby.startNotifier"));
        this.minPlayers = Integer.parseInt(pluginInstance.getPropertyConstant().get("generic.lobby.minPlayers"));

        this.prefix = pluginInstance.getMessageConstant().get("generic.lobby.prefix");
        this.countdownMessage = pluginInstance.getMessageConstant().get("generic.lobby.countdown");

        this.lobbyLocation = pluginInstance.getLocationConstant().get("game.pre.lobby");

        for (World world : Bukkit.getWorlds()) {
            world.setFullTime(6000);
            world.setThundering(false);
            world.setStorm(false);
            world.setTime(6000);
            world.setAutoSave(false);
            world.setDifficulty(Difficulty.PEACEFUL);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(this.lobbyLocation);
        }
    }

    @Override
    public void startCommandInitialization() {
        pluginInstance.getEventBus().registerCommand(new CommandDebug(pluginInstance, "debugging"));
    }

    @Override
    public void startListenerInitialization() {
        try {
            pluginInstance.getEventBus().linkEventSection("generic.listeners.pre",
                    (Listener[]) pluginInstance.getListenerConstant().get("generic.listeners.pre"));
        } catch (KernelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (this.currentCountdownPosition == 0 && Bukkit.getOnlinePlayers().size() >= this.minPlayers) {
            SoundHelper.playSound(Sound.ENTITY_PLAYER_LEVELUP, Source.MASTER, 1, 1);
            this.cancel();
        } else if (this.currentCountdownPosition == 0) {
            this.currentCountdownPosition = this.countdown;
        }

        if (this.currentCountdownPosition < this.startNotifier) {
            SoundHelper.playSound(Sound.ENTITY_EXPERIENCE_ORB_TOUCH, Source.MASTER, 1, 1);
        }

        ActionBarHelper.send(this.prefix + "§6" + FormatterHelper.formattedTimePattern(this.currentCountdownPosition) + this.countdownMessage);
        this.currentCountdownPosition--;
    }

}
