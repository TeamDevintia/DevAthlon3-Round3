package io.github.teamdevintia.round3;

import io.github.teamdevintia.round3.constants.ListenerConstant;
import io.github.teamdevintia.round3.constants.LocationConstant;
import io.github.teamdevintia.round3.constants.MessageConstant;
import io.github.teamdevintia.round3.event.EventBus;
import io.github.teamdevintia.round3.exceptions.KernelException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Round3 extends JavaPlugin {

    private static Round3 PLUGIN_INSTANCE;

    private EventBus eventBus;

    private MessageConstant messageConstant;
    private LocationConstant locationConstant;
    private ListenerConstant listenerConstant;

    public static Round3 getInstance() {
        return PLUGIN_INSTANCE;
    }

    @Override
    public void onEnable() {
        PLUGIN_INSTANCE = this;
        this.eventBus = new EventBus(this);
        this.initializeConstants();
        this.registerListeners();
    }

    @Override
    public void onDisable() {
        this.messageConstant.invalidateCaches();
    }

    private void initializeConstants() {
        this.messageConstant = new MessageConstant(getInstance());
        this.messageConstant.initializeContent();

        this.locationConstant = new LocationConstant(getInstance());
        this.locationConstant.initializeContent();

        this.listenerConstant = new ListenerConstant(getInstance());
        this.listenerConstant.initializeContent();
    }

    private void registerListeners() {
        try {
            this.eventBus.linkEventSection("generic.listeners.pre",
                    (Listener[]) this.listenerConstant.get("generic.listeners.pre"));
        } catch (KernelException e) {
            e.printStackTrace();
        }
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public MessageConstant getMessageConstant() {
        return messageConstant;
    }

    public LocationConstant getLocationConstant() {
        return locationConstant;
    }

    public ListenerConstant getListenerConstant() {
        return listenerConstant;
    }
}
