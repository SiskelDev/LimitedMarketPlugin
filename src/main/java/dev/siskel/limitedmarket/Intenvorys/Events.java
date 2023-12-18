package dev.siskel.limitedmarket.Intenvorys;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class Events implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;

        if (e.getClickedInventory().getHolder() instanceof FirstPage) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            if (e.getCurrentItem() == null) return;

            if (e.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE) {
                player.sendMessage("You Have Selected A Glass Pane");
                player.closeInventory();
            }
        }
    }
}
