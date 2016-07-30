package io.github.teamdevintia.round3.task.tasks;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.game.manifest.CommandDebug;
import io.github.teamdevintia.round3.task.Task;

/**
 * @author Shad0wCore
 */
public class ManifestTask extends Task {

    public ManifestTask(Round3 instance) {
        super(instance);
    }

    @Override
    public void run() {
        pluginInstance.getEventBus().registerCommand(new CommandDebug(pluginInstance, "debugging"));
    }

}
