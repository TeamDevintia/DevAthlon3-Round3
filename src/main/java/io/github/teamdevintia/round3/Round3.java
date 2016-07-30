package io.github.teamdevintia.round3;

import io.github.teamdevintia.round3.constants.ListenerConstant;
import io.github.teamdevintia.round3.constants.LocationConstant;
import io.github.teamdevintia.round3.constants.MessageConstant;
import io.github.teamdevintia.round3.constants.PropertyConstant;
import io.github.teamdevintia.round3.task.Task;
import io.github.teamdevintia.round3.task.TaskGoal;
import io.github.teamdevintia.round3.task.tasks.LobbyTask;
import io.github.teamdevintia.round3.task.tasks.InitializationTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Shad0wCore & batthomas
 */
public final class Round3 extends JavaPlugin {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
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
    private TaskGoal lobbyTaskGoal;

    public Round3() {
        this.messageConstant = new MessageConstant(this);
        this.locationConstant = new LocationConstant(this);
        this.listenerConstant = new ListenerConstant(this);
        this.propertyConstant = new PropertyConstant(this);
        this.lobbyTaskGoal = new TaskGoal(false, true, true, 0, 20);
    }

    @Override
    public void onEnable() {
        Task.executeTask(new InitializationTask(this), TaskGoal.defaultTaskGoal());
        Task.executeTask(this.lobbyTask = new LobbyTask(this), lobbyTaskGoal);
    }

    @Override
    public void onDisable() {
        this.messageConstant.invalidateCaches();
    }

}
