package io.github.teamdevintia.round3.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Shad0wCore
 */
public class TaskGoal {

    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)
    private boolean delayed, interval;
    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)
    private long delayValue, intervalValue;

    public TaskGoal(boolean delayed, boolean interval, long delayValue, long intervalValue) {
        this.setDelayed(delayed);
        this.setInterval(interval);
        this.setDelayValue(delayValue);
        this.setIntervalValue(intervalValue);
    }

    public static TaskGoal defaultTaskGoal() {
       return new TaskGoal(false, false, 0, 0);
    }

}
