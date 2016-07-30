package io.github.teamdevintia.round3.task.tasks;

import io.github.teamdevintia.round3.EventBus;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.game.manifest.CommandDebug;
import io.github.teamdevintia.round3.task.Task;
import org.bukkit.WorldCreator;

/**
 * @author Shad0wCore
 */
public class InitializationTask extends Task {

    public InitializationTask(Round3 pluginInstance) {
        super(pluginInstance);
    }

    @Override
    public void run() {
        instance.getPropertyConstant().initializeContent();
        instance.getMessageConstant().initializeContent();
        instance.getLocationConstant().initializeContent();
        instance.getListenerConstant().initializeContent();

        instance.setEventBus(new EventBus(instance));
        instance.getEventBus().registerCommand(new CommandDebug(instance, "debugging"));

        WorldCreator worldCreator = new WorldCreator(instance.getPropertyConstant().get("generic.worlds.lobby"));
        worldCreator.createWorld();
    }

}
