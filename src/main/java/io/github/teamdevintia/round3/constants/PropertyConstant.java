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
        this.put("generic.lobby.countdown", "90");
        this.put("generic.lobby.startNotifier", "6");
        this.put("generic.lobby.minPlayers", "8");

        this.put("generic.worlds.lobby", "lobby");
        this.put("generic.worlds.game", "game");
        this.put("generic.worlds.end", "lobby");
    }

}
