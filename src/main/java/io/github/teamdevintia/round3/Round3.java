package io.github.teamdevintia.round3;

import io.github.teamdevintia.round3.constants.ListenerConstant;
import io.github.teamdevintia.round3.constants.LocationConstant;
import io.github.teamdevintia.round3.constants.MessageConstant;
import io.github.teamdevintia.round3.constants.PropertyConstant;
import io.github.teamdevintia.round3.event.EventBus;
import io.github.teamdevintia.round3.exceptions.KernelException;
import io.github.teamdevintia.round3.game.pre.tasks.CountdownTask;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * @author Shad0wCore and batthomas
 */
public final class Round3 extends JavaPlugin {

    private static Round3 PLUGIN_INSTANCE;
    private EventBus eventBus;

    private MessageConstant messageConstant;
    private LocationConstant locationConstant;
    private ListenerConstant listenerConstant;
    private PropertyConstant propertyConstant;

    public static Round3 getInstance() {
        return PLUGIN_INSTANCE;
    }

    @Override
    public void onEnable() {
        PLUGIN_INSTANCE = this;
        this.eventBus = new EventBus(this);

        this.initializeConstants();
        this.registerListeners();
        this.startTasks();
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

        this.propertyConstant = new PropertyConstant(getInstance());
        this.propertyConstant.initializeContent();

    }

    private void registerListeners() {
        try {
            this.eventBus.linkEventSection("generic.listeners.pre", (Listener[]) this.listenerConstant.get("generic.listeners.pre"));
        } catch (KernelException e) {
            e.printStackTrace();
        }
    }

    private void startTasks() {
        CountdownTask countdownTask = new CountdownTask(PLUGIN_INSTANCE);
        countdownTask.runTaskTimer(PLUGIN_INSTANCE, 0, 20);
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

    public PropertyConstant getPropertyConstant() {
        return propertyConstant;
    }

}
