package io.github.teamdevintia.round3.game.pre.tasks;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.enums.Source;
import io.github.teamdevintia.round3.helper.ActionBarHelper;
import io.github.teamdevintia.round3.helper.SoundHelper;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Shad0wCore
 */
public class CountdownTask extends BukkitRunnable {

    private Round3 instance;
    private int currentCountdownPosition, countdown, startNotifier, minPlayers;
    private String prefix, countdownMessage;

    public CountdownTask(Round3 instance) {
        this.instance = instance;
        this.countdown = Integer.parseInt(this.instance.getPropertyConstant().get("generic.countdown"));
        this.currentCountdownPosition = this.countdown;
        this.startNotifier = Integer.parseInt(this.instance.getPropertyConstant().get("generic.startNotifier"));
        this.minPlayers = Integer.parseInt(this.instance.getPropertyConstant().get("generic.minPlayers"));

        this.prefix = this.instance.getMessageConstant().get("generic.prefix");
        this.countdownMessage = this.instance.getMessageConstant().get("generic.countdown");
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

        ActionBarHelper.send(this.prefix + "§6" + this.currentCountdownPosition + this.countdownMessage);
        this.currentCountdownPosition--;
    }

}
