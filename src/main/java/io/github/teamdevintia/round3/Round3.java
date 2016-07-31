package io.github.teamdevintia.round3;

import io.github.teamdevintia.round3.constants.*;
import io.github.teamdevintia.round3.managers.EntityInteractManager;
import io.github.teamdevintia.round3.managers.PanelManager;
import io.github.teamdevintia.round3.task.Task;
import io.github.teamdevintia.round3.task.TaskGoal;
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
    private PropertyConstant propertyConstant;
    @Getter(AccessLevel.PUBLIC)
    private ItemConstant itemConstant;
    @Getter(AccessLevel.PUBLIC)
    private NameTagConstant nameTagConstant;

    @Getter(AccessLevel.PUBLIC)
    private EntityInteractManager entityInteractManager;
    @Getter(AccessLevel.PUBLIC)
    private PanelManager panelManager;

    public Round3() {
        this.messageConstant = new MessageConstant(this);
        this.locationConstant = new LocationConstant(this);
        this.propertyConstant = new PropertyConstant(this);
        this.itemConstant = new ItemConstant(this);
        this.nameTagConstant = new NameTagConstant(this);
    }

    @Override
    public void onEnable() {
        this.eventBus = new EventBus(this);
        this.getEventBus().addStaticEvents(this.entityInteractManager = new EntityInteractManager(this), this.panelManager = new PanelManager(this));
        Task.executeTask(new InitializationTask(this), TaskGoal.defaultTaskGoal());
    }

}
