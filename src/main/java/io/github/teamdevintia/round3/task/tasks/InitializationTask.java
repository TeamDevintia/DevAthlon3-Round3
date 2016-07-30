package io.github.teamdevintia.round3.task.tasks;

import io.github.teamdevintia.round3.EventBus;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.game.manifest.CommandDebug;
import io.github.teamdevintia.round3.task.Task;
import io.github.teamdevintia.round3.task.TaskGoal;
import org.bukkit.WorldCreator;

/**
 * @author Shad0wCore
 */
public class InitializationTask extends Task {

    private TaskGoal lobbyTaskGoal = new TaskGoal(false, true, 0, 20);

    public InitializationTask(Round3 pluginInstance) {
        super(pluginInstance);
    }

    @Override
    public void run() {
        instance.setEventBus(new EventBus(instance));
        instance.getPropertyConstant().initializeContent();

        WorldCreator worldCreator = new WorldCreator(instance.getPropertyConstant().get("generics.worlds.lobby"));
        worldCreator.createWorld();

        instance.getMessageConstant().initializeContent();
        instance.getLocationConstant().initializeContent();
        instance.getListenerConstant().initializeContent();

        instance.getEventBus().registerCommand(new CommandDebug(instance, "debugging"));
        Task.executeTask(new GameLobbyTask(instance), lobbyTaskGoal);
    }

}
