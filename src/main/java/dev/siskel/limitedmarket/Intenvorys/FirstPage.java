package dev.siskel.limitedmarket.Intenvorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FirstPage implements InventoryHolder {

    public Inventory inv;

    public FirstPage() {
        inv = Bukkit.createInventory(this, 45, "Page");
        init();
    }

    private void init() {
        ItemStack item;
        for (int i = 0; i < 9; i++) {
            item = createItem("", Material.LIME_STAINED_GLASS_PANE, Collections.singletonList("Hi There"));

            inv.setItem(i, item);
        }

        for (int i = 35; i < 45; i++) {
            item = createItem("", Material.LIME_STAINED_GLASS_PANE, Collections.singletonList("Hi There"));

            inv.setItem(i, item);
        }
    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
