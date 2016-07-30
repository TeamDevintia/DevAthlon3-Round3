package io.github.teamdevintia.round3;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Shad0wCore
 */
public abstract class Task extends BukkitRunnable {

    protected Round3 instance;

    public Task(Round3 instance) {
        this.instance = instance;
    }

    public void startListenerInitialization() {

    }

    public void startCommandInitialization() {

    }

}
