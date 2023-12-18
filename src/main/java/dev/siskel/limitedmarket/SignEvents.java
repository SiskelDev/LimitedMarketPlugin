package dev.siskel.limitedmarket;

import dev.siskel.limitedmarket.Intenvorys.FirstPage;
import jdk.xml.internal.XMLSecurityManager;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sun.java2d.pipe.SpanShapeRenderer;

public class SignEvents implements Listener {

    FileConfiguration config;
    public Economy econ;

    public SignEvents(FileConfiguration config) {
        econ = LimitedMarket.getEconomy();
        this.config = config;
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if(e.getLine(0).equalsIgnoreCase("[LimitedMarket]")) {
            if (e.getLine(1).charAt(0) == '+') {
                e.setLine(0, ChatColor.GREEN + "[LimitedMarket]");
                e.setLine(1, ChatColor.GREEN + e.getLine(1));
                e.setLine(2, ChatColor.RED + e.getLine(2));
            } else if (e.getLine(1).charAt(0) == '-') {
                e.setLine(0, ChatColor.RED + "[LimitedMarket]");
                e.setLine(1, ChatColor.RED + e.getLine(1));
                e.setLine(2, ChatColor.GREEN + e.getLine(2));
            } else {
                e.setLine(0, ChatColor.BLUE + "[LimitedMarket]");
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        double change;
        int count;
        char whatToDO, whatBlock;
        String item;


        if (e.getClickedBlock() == null) return;

        if (e.getClickedBlock().getState() instanceof Sign) {
            FirstPage gui = new FirstPage();

            e.getPlayer().openInventory(gui.getInventory());
            e.getPlayer().sendMessage(ChatColor.AQUA + "Hi You Opened that!");
        }

        /*
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign sign = (Sign)e.getClickedBlock().getState();
            VaultItemManagement vc = new VaultItemManagement(e.getPlayer(), sign, this.config);


            if (sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[LimitedMarket]")) {
                if (sign.getLine(1) != "" && sign.getLine(2) != "" ) {
                    int lastIndex;

                    change = Double.valueOf(sign
                            .getLine(1)
                            .replace(ChatColor.GREEN.toString(), "")
                            .replace(ChatColor.RED.toString(), "")
                            .substring(1)
                    );

                    whatBlock = sign.getLine(2)
                            .replace(ChatColor.GREEN.toString(), "")
                            .replace(ChatColor.RED.toString(), "")
                            .charAt(0);

                    item = sign
                            .getLine(2)
                            .replace(ChatColor.GREEN.toString(), "")
                            .replace(ChatColor.RED.toString(), "");

                    count = Integer.valueOf(item
                            .replace(ChatColor.GREEN.toString(), "")
                            .replace(ChatColor.RED.toString(), "")
                            .substring(item
                                    .lastIndexOf(" ") + 2
                            )
                    );

                    whatToDO = item.substring(item.lastIndexOf(" ") + 1).charAt(0);

                    ItemStack current = e.getPlayer().getInventory().getItemInMainHand();

                    item = item
                            .substring(0, item
                                    .lastIndexOf(" ")
                            );

                    if (whatToDO == '+') {
                        vc.economyToItem(vc.stringToMaterial(item), count, change);
                    } else if (whatToDO == '-') {
                        LimitedMarket.console.sendMessage(item);
                        vc.itemToEconomy(vc.stringToMaterial(item), count, change);
                    }
                } else {
                    e.getPlayer().sendMessage("Please Add an Amount to Withdraw or deposit: Invalid -> \"" + sign.getLine(1) + "\"");
                    return;
                }
            }
        }
        */
    }
}
