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
        this.put("generics.lobby.countdown", String.valueOf(90));
        this.put("generics.lobby.startNotifier", String.valueOf(6));
        this.put("generics.lobby.minPlayers", String.valueOf(8));

        this.put("generics.build.countdown", String.valueOf(60 * 5));
        this.put("generics.build.startNotifier", String.valueOf(10));

        this.put("generics.worlds.lobby", "lobby");
        this.put("generics.worlds.build", "build");
        this.put("generics.worlds.end", "lobby");
    }

}
