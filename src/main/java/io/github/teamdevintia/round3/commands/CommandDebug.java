package io.github.teamdevintia.round3.commands;

import io.github.teamdevintia.round3.CommandHandler;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.task.tasks.GameLobbyTask;
import org.bukkit.command.CommandSender;

/**
 * @author Shad0wCore
 */
public class CommandDebug extends CommandHandler {

    public CommandDebug(Round3 instance, String name) {
        super(instance, name);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        switch (strings[0]) {
            case "skipLobby":
                GameLobbyTask.getOriginInstance().endingTriggered();
                break;
        }
        return true;
    }

}
