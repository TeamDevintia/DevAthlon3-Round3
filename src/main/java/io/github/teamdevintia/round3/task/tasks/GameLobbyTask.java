package io.github.teamdevintia.round3.task.tasks;

import io.github.teamdevintia.round3.ListenerHandler;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.enums.Source;
import io.github.teamdevintia.round3.exceptions.KernelException;
import io.github.teamdevintia.round3.helper.ActionBarHelper;
import io.github.teamdevintia.round3.helper.FormatterHelper;
import io.github.teamdevintia.round3.helper.SoundHelper;
import io.github.teamdevintia.round3.listeners.pre.*;
import io.github.teamdevintia.round3.task.Task;
import io.github.teamdevintia.round3.task.TaskGoal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * @author Shad0wCore
 */
public class GameLobbyTask extends Task {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private int currentCountdownPosition, countdown, startNotifier, minPlayers;
    @Getter(AccessLevel.PUBLIC)
    private String prefix, countdownMessage;

    private Location lobbyLocation;

    public GameLobbyTask(Round3 instance) {
        super(instance);
        this.countdown = Integer.parseInt(instance.getPropertyConstant().get("generics.lobby.countdown"));
        this.currentCountdownPosition = this.countdown;
        this.startNotifier = Integer.parseInt(instance.getPropertyConstant().get("generics.lobby.startNotifier"));
        this.minPlayers = Integer.parseInt(instance.getPropertyConstant().get("generics.lobby.minPlayers"));
        this.prefix = instance.getMessageConstant().get("generics.prefix");
        this.countdownMessage = instance.getMessageConstant().get("generics.lobby.countdown");
        this.lobbyLocation = instance.getLocationConstant().get("generics.locations.lobby");

        for (World world : Bukkit.getWorlds()) {
            world.setFullTime(6000);
            world.setThundering(false);
            world.setStorm(false);
            world.setTime(6000);
            world.setAutoSave(false);
            world.setDifficulty(Difficulty.PEACEFUL);
            world.getEntities().forEach(Entity::remove);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(this.lobbyLocation);
        }
    }

    @Override
    public void initializationTriggered() {
        try {
            instance.getEventBus().linkEventSection("generics.listeners.pre", (Listener[]) new ListenerHandler[]{new PrePlayerListener(instance),
                    new PreDamageListener(instance), new PreInteractListener(instance), new PreBlockListener(instance), new PreWorldListener(instance)});
        } catch (KernelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endingTriggered() {
        try {
            this.cancel();
            instance.getEventBus().releaseEventSection("generics.listeners.pre");
            Task.executeTask(new GameBuildStartTask(instance), new TaskGoal(false, true, 0, 20));
        } catch (KernelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (this.currentCountdownPosition == 0 && Bukkit.getOnlinePlayers().size() >= this.minPlayers) {
            this.endingTriggered();
            return;
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
