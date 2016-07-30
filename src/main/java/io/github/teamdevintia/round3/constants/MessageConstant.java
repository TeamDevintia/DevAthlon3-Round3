package io.github.teamdevintia.round3.constants;

import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.handlers.ConstantHandler;

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
        this.put("game.pre.join", this.get("generic.prefix") + "§7{0} §fhat den Server betreten§8.");
        this.put("game.pre.quit", this.get("generic.prefix") + "§7{0} §fhat den Server verlassen8.");
    }

}
