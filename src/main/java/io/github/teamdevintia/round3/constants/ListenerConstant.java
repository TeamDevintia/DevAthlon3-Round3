package io.github.teamdevintia.round3.constants;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.game.pre.listeners.*;
import io.github.teamdevintia.round3.ConstantHandler;
import io.github.teamdevintia.round3.ListenerHandler;

/**
 * @author Shad0wCore
 */
public class ListenerConstant extends ConstantHandler<ListenerHandler[]> {

    public ListenerConstant(Round3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.put("generic.listeners.pre", new ListenerHandler[]{new PrePlayerListener(instance), new PreDamageListener(instance),
                new PreInteractListener(instance), new PreBlockListener(instance), new PreWorldListener(instance)});
    }

}
