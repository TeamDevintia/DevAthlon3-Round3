package io.github.teamdevintia.round3.task;

import io.github.teamdevintia.round3.Round3;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Shad0wCore
 */
public abstract class Task extends BukkitRunnable {

    protected static Round3 instance;

    public Task(Round3 pluginInstance) {
        instance = pluginInstance;
    }

    public static void executeTask(Task task, TaskGoal taskGoal) {
        if (taskGoal.isListenerInitFirst()) {
            task.startListenerInitialization();
            task.startCommandInitialization();
        } else {
            task.startCommandInitialization();
            task.startListenerInitialization();
        }

        if (taskGoal.isInterval()) {
            task.runTaskTimer(instance, taskGoal.getDelayValue(), taskGoal.getIntervalValue());
        } else if (taskGoal.isDelayed()) {
            task.runTaskLater(instance, taskGoal.getDelayValue());
        } else {
            task.run();
        }
    }

    public void startListenerInitialization() {

    }

    public void startCommandInitialization() {

    }

}
