package io.github.teamdevintia.round3.event;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.exceptions.KernelException;
import io.github.teamdevintia.round3.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;

import java.util.HashMap;

/**
 * @author Shad0wCore
 */

public final class EventBus {

    private Round3 library;
    private PluginManager pluginManager;
    private HashMap<String, HandlerList> handlerListHashMap;

    public EventBus(Round3 library) {
        this.library = library;
        this.pluginManager = this.library.getServer().getPluginManager();
        this.handlerListHashMap = new HashMap<>();
    }

    public void addStaticEvent(Listener listener) {
        this.pluginManager.registerEvents(listener, this.library);
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
            }, EventPriority.NORMAL, this.library, false));
            this.pluginManager.registerEvents(listener, this.library);
        }

        this.handlerListHashMap.put(identifier, handlerList);
    }

    public void releaseEventSection(String identifier) throws KernelException {
        if (this.handlerListHashMap.get(identifier) == null)
            throw new KernelException("HandlerList with ID: " + identifier + " is not registered");

        for (RegisteredListener registeredListener : this.handlerListHashMap.get(identifier).getRegisteredListeners()) {
            HandlerList.unregisterAll(registeredListener.getListener());
        }
    }

    public void registerCommand(CommandHandler commandHandler) {
        ((CraftServer) Bukkit.getServer()).getCommandMap().register(commandHandler.getName(), commandHandler);
    }

}
