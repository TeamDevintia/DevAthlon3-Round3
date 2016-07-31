package io.github.teamdevintia.round3.task.tasks;

import io.github.teamdevintia.round3.ListenerHandler;
import io.github.teamdevintia.round3.NameTag;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.enums.Source;
import io.github.teamdevintia.round3.exceptions.KernelException;
import io.github.teamdevintia.round3.helper.*;
import io.github.teamdevintia.round3.listeners.build.BuildBlockListener;
import io.github.teamdevintia.round3.listeners.build.BuildDamageListener;
import io.github.teamdevintia.round3.task.Task;
import io.github.teamdevintia.round3.task.TaskGoal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Random;

/**
 * @author Shad0wCore
 */
public class GameBuildStartTask extends Task {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private int currentCountdownPosition, countdown, startNotifier;
    @Getter(AccessLevel.PUBLIC)
    private String prefix, countdownMessage;
    private NameTag nameTagRed, nameTagBlue;
    private Location teamBlueScatter1, teamBlueScatter2;
    private Location teamRedScatter1, teamRedScatter2;
    private Random random = new Random();

    public GameBuildStartTask(Round3 instance) {
        super(instance);
        this.countdown = Integer.parseInt(instance.getPropertyConstant().get("generics.build.countdown"));
        this.currentCountdownPosition = this.countdown;
        this.countdownMessage = instance.getMessageConstant().get("generics.lobby.countdown");
        this.prefix = instance.getMessageConstant().get("generics.prefix");
        this.nameTagRed = instance.getNameTagConstant().get("generics.nametags.teamRed");
        this.nameTagBlue = instance.getNameTagConstant().get("generics.nametags.teamBlue");

        this.teamBlueScatter1 = instance.getLocationConstant().get("generics.locations.teamBlueScatter1");
        this.teamBlueScatter2 = instance.getLocationConstant().get("generics.locations.teamBlueScatter2");

        this.teamRedScatter1 = instance.getLocationConstant().get("generics.locations.teamRedScatter1");
        this.teamRedScatter2 = instance.getLocationConstant().get("generics.locations.teamRedScatter2");
    }

    @Override
    public void initializationTriggered() {
        try {
            SoundHelper.playSound(Sound.ENTITY_PLAYER_LEVELUP, Source.MASTER, 1, 1);
            instance.getEventBus().linkEventSection("generics.listeners.build",
                    (Listener[]) new ListenerHandler[]{new BuildDamageListener(instance), new BuildBlockListener(instance)});
        } catch (KernelException e) {
            e.printStackTrace();
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!TeamHelper.contains(player, this.nameTagRed) && !TeamHelper.contains(player, this.nameTagBlue)) {
                switch (random.nextInt(1)) {
                    case 0:
                        TeamHelper.add(player, this.nameTagRed);
                        break;
                    case 1:
                        TeamHelper.add(player, this.nameTagBlue);
                        break;
                }
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (TeamHelper.contains(player, this.nameTagBlue)) {
                player.teleport(LocationHelper.getRandomLocation(this.teamBlueScatter1, this.teamBlueScatter2));
            } else {
                player.teleport(LocationHelper.getRandomLocation(this.teamRedScatter1, this.teamRedScatter2));
            }
        }
    }

    @Override
    public void endingTriggered() {
        try {
            this.cancel();
            instance.getEventBus().releaseEventSection("generics.listeners.build");
            Task.executeTask(new GameStartTask(instance), TaskGoal.defaultTaskGoal());
        } catch (KernelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (this.currentCountdownPosition == 0) {
            this.endingTriggered();
            return;
        }

        if (this.currentCountdownPosition < this.startNotifier) {
            SoundHelper.playSound(Sound.ENTITY_EXPERIENCE_ORB_TOUCH, Source.MASTER, 1, 2);
        }

        ActionBarHelper.send(this.prefix + "§6" + FormatterHelper.formattedTimePattern(this.currentCountdownPosition) + this.countdownMessage);
        this.currentCountdownPosition--;
    }

}
