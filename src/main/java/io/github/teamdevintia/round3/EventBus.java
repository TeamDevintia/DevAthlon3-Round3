package io.github.teamdevintia.round3;

import io.github.teamdevintia.round3.exceptions.KernelException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.help.GenericCommandHelpTopic;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicComparator;
import org.bukkit.help.IndexHelpTopic;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Shad0wCore
 */

public final class EventBus {

    private Round3 instance;
    private PluginManager pluginManager;
    private HashMap<String, HandlerList> handlerListHashMap;
    private Map<String, CommandHandler> commandMap;


    public EventBus(Round3 instance) {
        this.instance = instance;
        this.pluginManager = this.instance.getServer().getPluginManager();
        this.handlerListHashMap = new HashMap<>();
        this.commandMap = new HashMap<>();
    }

    public void addStaticEvent(Listener listener) {
        this.pluginManager.registerEvents(listener, this.instance);
    }

    public void addStaticEvents(Listener... listeners) {
        for (Listener listener : listeners)
            this.addStaticEvent(listener);
    }

    public void linkEventSection(String identifier, Listener... listeners) throws KernelException {
        if (this.handlerListHashMap.get(identifier) != null) {
            throw new KernelException("HandlerList with ID: " + identifier + " has been already registered");
        }

        HandlerList handlerList = new HandlerList();

        for (Listener listener : listeners) {
            handlerList.register(new RegisteredListener(listener, (listener1, event) -> {
            }, EventPriority.NORMAL, this.instance, false));
            this.pluginManager.registerEvents(listener, this.instance);
        }

        this.handlerListHashMap.put(identifier, handlerList);
    }

    public void releaseEventSection(String identifier) throws KernelException {
        if (this.handlerListHashMap.get(identifier) == null)
            throw new KernelException("HandlerList with ID: " + identifier + " is not registered");

        for (RegisteredListener registeredListener : this.handlerListHashMap.get(identifier).getRegisteredListeners()) {
            HandlerList.unregisterAll(registeredListener.getListener());
        }

        this.handlerListHashMap.remove(identifier);
    }

    public void registerCommand(CommandHandler commandHandler) {
        commandMap.put(commandHandler.getName(), commandHandler);
        ((CraftServer) Bukkit.getServer()).getCommandMap().register(commandHandler.getName(), commandHandler);
    }

    private void registerHelp() {
        Set<HelpTopic> help = new TreeSet<>(HelpTopicComparator.helpTopicComparatorInstance());
        commandMap.keySet().stream().filter(commandLabel -> !commandLabel.contains(".")).forEach(commandLabel -> {
            Command cmd = ((CraftServer) Bukkit.getServer()).getCommandMap().getCommand(commandLabel);
            HelpTopic topic = new GenericCommandHelpTopic(cmd);
            help.add(topic);
        });
        IndexHelpTopic topic = new IndexHelpTopic(instance.getName(), "All commands for "
                + instance.getName(), instance.getName() + ".help", help, "Below is a list of all " + instance.getName() + " commands:");
        Bukkit.getServer().getHelpMap().addTopic(topic);
        help.forEach((t) -> Bukkit.getServer().getHelpMap().addTopic(t));
    }


}
