package io.github.teamdevintia.round3.task.tasks;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.commands.CommandDebug;
import io.github.teamdevintia.round3.helper.TeamHelper;
import io.github.teamdevintia.round3.panels.TeamPanel;
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
        instance.getPropertyConstant().initializeContent();

        WorldCreator worldCreator = new WorldCreator(instance.getPropertyConstant().get("generics.worlds.lobby"));
        worldCreator.createWorld();

        instance.getMessageConstant().initializeContent();
        instance.getLocationConstant().initializeContent();
        instance.getItemConstant().initializeContent();
        instance.getNameTagConstant().initializeContent();
        instance.getEventBus().registerCommand(new CommandDebug(instance, "debugging"));
        instance.getPanelManager().bindNewPanel("teamPanel", new TeamPanel(instance, "§7Wähle dein Team§8.", 9));

        TeamHelper.registerTeam(instance.getNameTagConstant().get("generics.nametags.teamRed"));
        TeamHelper.registerTeam(instance.getNameTagConstant().get("generics.nametags.teamBlue"));
        Task.executeTask(new GameLobbyTask(instance), lobbyTaskGoal);
    }

}
