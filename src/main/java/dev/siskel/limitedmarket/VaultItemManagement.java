package dev.siskel.limitedmarket;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.CountDownLatch;

public class VaultItemManagement {
    Player e;
    Sign sign;
    Economy econ = LimitedMarket.getEconomy();
    FileConfiguration config;

    public VaultItemManagement(Player e, Sign sign, FileConfiguration config) {
        this.e = e;
        this.sign = sign;
        this.config = config;
    }

    public Material stringToMaterial(String mat) {
        return Material.matchMaterial(mat);
    }

    public String materialToString(Material mat) {
        return mat.getBlockTranslationKey();
    }

    public void itemToEconomy(Material item, int itemsToRemove, double currencyAmount) {
        int countingUpForItem = 0;

        for (ItemStack itemInInv : e.getInventory()) {
            if (itemInInv != null && itemInInv.getType() == item) {
                countingUpForItem += itemInInv.getAmount();
            }
        }

        if (!(countingUpForItem >= itemsToRemove)) {
            e.sendMessage("You don't have enough " + item.name() + " to get " + currencyAmount + " Coins");
            return;
        }

        econ.depositPlayer(this.e, currencyAmount);

        for (int i = 0; i < itemsToRemove; i++) {
            for (ItemStack itemInInv : e.getInventory()) {
                if (itemInInv != null && itemInInv.getType() == item) {
                    itemInInv.setAmount(itemInInv.getAmount() - 1);
                }
            }
        }
    }

    public void economyToItem(Material item, int itemsToAdd, double currencyAmount) {
        econ.depositPlayer(this.e, currencyAmount);

        ItemStack items = new ItemStack(item, itemsToAdd);
        e.getInventory().addItem(items);
    }
}
