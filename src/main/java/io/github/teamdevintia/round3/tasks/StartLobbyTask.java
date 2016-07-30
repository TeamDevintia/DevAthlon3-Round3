package io.github.teamdevintia.round3.tasks;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.Task;
import io.github.teamdevintia.round3.enums.Source;
import io.github.teamdevintia.round3.exceptions.KernelException;
import io.github.teamdevintia.round3.game.manifest.CommandDebug;
import io.github.teamdevintia.round3.helper.ActionBarHelper;
import io.github.teamdevintia.round3.helper.FormatterHelper;
import io.github.teamdevintia.round3.helper.SoundHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * @author Shad0wCore
 */
public class StartLobbyTask extends Task {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private int currentCountdownPosition, countdown, startNotifier, minPlayers;
    @Getter(AccessLevel.PUBLIC)
    private String prefix, countdownMessage;

    public StartLobbyTask(Round3 instance) {
        super(instance);
        this.countdown = Integer.parseInt(this.instance.getPropertyConstant().get("generic.lobby.countdown"));
        this.currentCountdownPosition = this.countdown;
        this.startNotifier = Integer.parseInt(this.instance.getPropertyConstant().get("generic.lobby.startNotifier"));
        this.minPlayers = Integer.parseInt(this.instance.getPropertyConstant().get("generic.lobby.minPlayers"));

        this.prefix = this.instance.getMessageConstant().get("generic.lobby.prefix");
        this.countdownMessage = this.instance.getMessageConstant().get("generic.lobby.countdown");

        for (World world : Bukkit.getWorlds()) {
            world.setFullTime(6000);
            world.setThundering(false);
            world.setStorm(false);
            world.setTime(6000);
            world.setAutoSave(false);
            world.setDifficulty(Difficulty.PEACEFUL);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {

        }
    }

    @Override
    public void startCommandInitialization() {
        this.instance.getEventBus().registerCommand(new CommandDebug(this.instance, "debugging"));
    }

    @Override
    public void startListenerInitialization() {
        try {
            this.instance.getEventBus().linkEventSection("generic.listeners.pre",
                    (Listener[]) this.instance.getListenerConstant().get("generic.listeners.pre"));
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
