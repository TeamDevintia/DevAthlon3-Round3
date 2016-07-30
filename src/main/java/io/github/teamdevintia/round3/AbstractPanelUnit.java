package io.github.teamdevintia.round3;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author Shad0wCore
 */
public abstract class AbstractPanelUnit {

    private Inventory menuInventory;
    private Round3 instance;

    public AbstractPanelUnit(Round3 instance, String inventoryTitle, int inventorySize) {
        this.instance = instance;
        this.menuInventory = Bukkit.createInventory(null, inventorySize, inventoryTitle);
    }

    public abstract void triggeredClick(ItemStack clickedItem, int slot, Player player);

    public abstract void triggeredEscape(InventoryCloseEvent event);

    public abstract void initialization();

    public Inventory panel() {
        return this.menuInventory;
    }

    public int getSize() {
        return this.panel().getSize();
    }

    public ItemStack[] getContents() {
        return this.panel().getContents();
    }

    public String getName() {
        return this.panel().getName();
    }

    public Round3 getInstance() {
        return instance;
    }
}
