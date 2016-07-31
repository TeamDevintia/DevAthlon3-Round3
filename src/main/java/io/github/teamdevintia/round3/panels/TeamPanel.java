package io.github.teamdevintia.round3.panels;

import io.github.teamdevintia.round3.NameTag;
import io.github.teamdevintia.round3.PanelUnit;
import io.github.teamdevintia.round3.Round3;
import io.github.teamdevintia.round3.helper.TeamHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Shad0wCore
 */
public class TeamPanel extends PanelUnit {

    private NameTag nameTagRed, nameTagBlue;

    public TeamPanel(Round3 instance, String inventoryTitle, int inventorySize) {
        super(instance, inventoryTitle, inventorySize);
        this.nameTagRed = instance.getNameTagConstant().get("generics.nametags.teamRed");
        this.nameTagBlue = instance.getNameTagConstant().get("generics.nametags.teamBlue");
    }

    @Override
    public void triggeredClick(ItemStack clickedItem, int slot, Player player) {
        switch (slot) {
            case 3:
                if (TeamHelper.contains(player, this.nameTagBlue)) {
                    TeamHelper.get(this.nameTagBlue).removeEntry(player.getName());
                }
                TeamHelper.add(player, this.nameTagRed);
                clickedItem.setAmount(TeamHelper.get(this.nameTagRed).getSize());
                panel().getItem(5).setAmount(TeamHelper.get(this.nameTagBlue).getSize());
                break;
            case 5:
                if (TeamHelper.contains(player, this.nameTagRed)) {
                    TeamHelper.get(this.nameTagRed).removeEntry(player.getName());
                }
                TeamHelper.add(player, this.nameTagBlue);
                clickedItem.setAmount(TeamHelper.get(this.nameTagBlue).getSize());
                panel().getItem(3).setAmount(TeamHelper.get(this.nameTagRed).getSize());
                break;
        }
    }

    @Override
    public void triggeredEscape(InventoryCloseEvent event) {

    }

    @Override
    public void initialization() {
        this.panel().setItem(3, instance.getItemConstant().get("generics.panel.team.teamRed"));
        this.panel().setItem(5, instance.getItemConstant().get("generics.panel.team.teamBlue"));
    }

}
