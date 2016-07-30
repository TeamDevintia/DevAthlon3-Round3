package io.github.teamdevintia.round3.constants;

import io.github.teamdevintia.round3.ConstantHandler;
import io.github.teamdevintia.round3.Round3;

/**
 * @author Shad0wCore
 */
public class PropertyConstant extends ConstantHandler<String> {

    public PropertyConstant(Round3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.put("generic.countdown", "90");
        this.put("generic.startNotifier", "6");

        this.put("generic.minPlayers", "8");
    }

}
