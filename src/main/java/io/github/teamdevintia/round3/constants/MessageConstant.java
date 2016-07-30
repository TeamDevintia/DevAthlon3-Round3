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
        this.put("generics.prefix", "§8[ §3Islands §8> §f");
        this.put("generics.lobby.countdown", " §fbis §8'§7Islands§8' §fstartet§8.");

        this.put("generics.lobby.join", " §fhat den Server betreten§8.");
        this.put("generics.lobby.quit", " §fhat den Server verlassen§8.");

    }

}
