package dev.siskel.limitedmarket;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SignEvents implements Listener {

    public Economy econ;

    public SignEvents() {
        econ = LimitedMarket.getEconomy();
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if(e.getLine(0).equalsIgnoreCase("[LimitedMarket]")) {
            e.setLine(0, ChatColor.GREEN + "[LimitedMarket]");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        double change;
        char whatToDO, whatBlock;
        String item;

        if (e.getClickedBlock() == null) return;

        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign sign = (Sign)e.getClickedBlock().getState();
            if (sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[LimitedMarket]")) {
                if (sign.getLine(1) != "") {
                    whatToDO = sign.getLine(1).charAt(0);
                    change = Double.valueOf(sign.getLine(1).substring(1));

                    if (whatToDO == '+') {
                        econ.depositPlayer(e.getPlayer(), change);
                        e.getPlayer().sendMessage("You got " + change + " Coin/s!");
                    } else if (whatToDO == '-') {
                        econ.withdrawPlayer(e.getPlayer(), change);
                        e.getPlayer().sendMessage("You lost " + change + " Coin/s!");
                    } else {
                        e.getPlayer().sendMessage("You have to Define a + or - at the beginning of the Amount: Invalid -> \"" + sign.getLine(1) + "\"");
                        return;
                    }
                }
                else {
                    e.getPlayer().sendMessage("Please Add an Amount to Withdraw or deposit: Invalid -> \"" + sign.getLine(1) + "\"");
                    return;
                }

                if (sign.getLine(2) != "") {
                    whatBlock = sign.getLine(2).charAt(0);
                    item = sign.getLine(2).substring(1);
                    ItemStack current = e.getPlayer().getInventory().getItemInMainHand();

                    if (whatBlock == '+') {
                        if (current.getType() == Material.DIAMOND) {
                            current.setAmount(current.getAmount() + 1);
                        }
                        e.getPlayer().sendMessage("You got " + item);
                    } else if (whatBlock == '-') {
                        if (current.getType() == Material.DIAMOND) {
                            current.setAmount(current.getAmount() - 1);
                        }
                        e.getPlayer().sendMessage("You Lost " + item);
                    } else {
                        e.getPlayer().sendMessage("You have to Define a + or - at the beginning of the Block: Invalid -> \"" + sign.getLine(2) + "\"");
                        return;
                    }
                }
                else {
                    e.getPlayer().sendMessage("Please Add a Block to be taken away/to be added: Invalid -> \"" + sign.getLine(2) + "\"");
                    return;
                }
            }
        }
    }
}
