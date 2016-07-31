package io.github.teamdevintia.round3.constants;

import io.github.teamdevintia.round3.ConstantHandler;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.helper.ItemFactory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

/**
 * @author Shad0wCore
 */
public class ItemConstant extends ConstantHandler<ItemStack> {

    public ItemConstant(Round3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {

        this.put("generics.panel.team.teamRed", new ItemFactory(Material.STAINED_CLAY)
                .amount(0).durability(14).displayName("§8Team §cRot").itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .lore("§7Betrete das Team §cRot").release());

        this.put("generics.panel.team.teamBlue", new ItemFactory(Material.STAINED_CLAY)
                .amount(0).durability(3).displayName("§8Team §9Blau").itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .lore("§7Betrete das Team §9Blau").release());

    }

}
