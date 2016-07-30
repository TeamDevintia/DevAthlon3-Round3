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
        this.put("generics.lobby.countdown", "90");
        this.put("generics.lobby.startNotifier", "6");
        this.put("generics.lobby.minPlayers", "8");

        this.put("generics.worlds.lobby", "lobby");
        this.put("generics.worlds.game", "game");
        this.put("generics.worlds.end", "lobby");
    }

}
