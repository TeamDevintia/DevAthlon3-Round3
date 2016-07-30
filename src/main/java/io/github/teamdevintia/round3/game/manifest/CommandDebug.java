package io.github.teamdevintia.round3.game.manifest;

import io.github.teamdevintia.round3.CommandHandler;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.exceptions.KernelException;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

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
            case "releaseLobbyEvents":
                try {
                    this.getInstance().getEventBus().releaseEventSection("generic.listeners.pre");
                } catch (KernelException e) {
                    e.printStackTrace();
                }
                break;
            case "linkLobbyEvents":
                try {
                    this.getInstance().getEventBus().linkEventSection("generic.listeners.pre",
                            (Listener[]) getInstance().getListenerConstant().get("generic.listeners.pre"));
                } catch (KernelException e) {
                    e.printStackTrace();
                }
                break;
            case "skipLobby":
                
                break;
        }
        return true;
    }

}
