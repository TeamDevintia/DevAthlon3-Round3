package io.github.teamdevintia.round3.constants;

import io.github.teamdevintia.round3.ConstantHandler;
import io.github.teamdevintia.round3.NameTag;
import io.github.teamdevintia.round3.Round3;

/**
 * @author Shad0wCore
 */
public class NameTagConstant extends ConstantHandler<NameTag> {

    public NameTagConstant(Round3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.put("generics.nametags.teamRed", new NameTag("§8[§cRot§8> §7", "", "teamRed"));
        this.put("generics.nametags.teamBlue", new NameTag("§8[§9Blau§8> §7", "", "teamBlue"));
    }

}
