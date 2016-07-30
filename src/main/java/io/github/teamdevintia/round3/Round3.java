package io.github.teamdevintia.round3;

import io.github.teamdevintia.round3.constants.ListenerConstant;
import io.github.teamdevintia.round3.constants.LocationConstant;
import io.github.teamdevintia.round3.constants.MessageConstant;
import io.github.teamdevintia.round3.constants.PropertyConstant;
import io.github.teamdevintia.round3.task.Task;
import io.github.teamdevintia.round3.task.TaskGoal;
import io.github.teamdevintia.round3.task.tasks.LobbyTask;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Shad0wCore and batthomas
 */
public final class Round3 extends JavaPlugin {

    @Getter(AccessLevel.PUBLIC)
    private EventBus eventBus;
    @Getter(AccessLevel.PUBLIC)
    private MessageConstant messageConstant;
    @Getter(AccessLevel.PUBLIC)
    private LocationConstant locationConstant;
    @Getter(AccessLevel.PUBLIC)
    private ListenerConstant listenerConstant;
    @Getter(AccessLevel.PUBLIC)
    private PropertyConstant propertyConstant;

    @Getter(AccessLevel.PUBLIC)
    private LobbyTask lobbyTask;

    public Round3() {
        this.messageConstant = new MessageConstant(this);
        this.messageConstant.initializeContent();

        this.locationConstant = new LocationConstant(this);
        this.locationConstant.initializeContent();

        this.listenerConstant = new ListenerConstant(this);
        this.listenerConstant.initializeContent();

        this.propertyConstant = new PropertyConstant(this);
        this.propertyConstant.initializeContent();
    }

    @Override
    public void onEnable() {
        this.initializeEventBus();
        TaskGoal lobbyTaskGoal = new TaskGoal(false, false, true, 0, 0);
        Task.executeTask(this.lobbyTask = new LobbyTask(this), lobbyTaskGoal);
    }

    @Override
    public void onDisable() {
        this.messageConstant.invalidateCaches();
    }

    private void initializeEventBus() {
        this.eventBus = new EventBus(this);
    }

}
