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
    private boolean delayed = false, interval = false, listenerInitFirst = true;
    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)
    private long delayValue = 0, intervalValue = 0;

    public TaskGoal() {

    }

    public TaskGoal(boolean delayed, boolean interval, boolean listenerInitFirst, long delayValue, long intervalValue) {
        this.setDelayed(delayed);
        this.setInterval(interval);
        this.setListenerInitFirst(listenerInitFirst);
        this.setDelayValue(delayValue);
        this.setIntervalValue(intervalValue);
    }

}
