package io.github.teamdevintia.round3;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Shad0wCore
 */
public abstract class CommandHandler extends BukkitCommand {

    private Round3 instance;

    public CommandHandler(Round3 instance, String name) {
        super(name);
        this.instance = instance;
    }

    public CommandHandler(Round3 instance, String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
        this.instance = instance;
    }

    public final Round3 getInstance() {
        return this.instance;
    }

    public final Player fromCommandSender(CommandSender commandSender) {
        return ((Player) commandSender);
    }

    public final boolean isNotCommandSender(CommandSender commandSender) {
        return commandSender instanceof Player;
    }


}
