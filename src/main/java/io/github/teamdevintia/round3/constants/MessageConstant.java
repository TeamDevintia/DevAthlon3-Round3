package io.github.teamdevintia.round3.constants;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.ConstantHandler;

/**
 * @author Shad0wCore
 */
public class MessageConstant extends ConstantHandler<String> {

    public MessageConstant(Round3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.put("generic.prefix", "§8[ §3Islands §8> §f");
        this.put("generic.countdown", " §fbis §8'§7Islands§8' §fstartet§8.");

        this.put("game.pre.join", " §fhat den Server betreten§8.");
        this.put("game.pre.quit", " §fhat den Server verlassen8.");

    }

}
