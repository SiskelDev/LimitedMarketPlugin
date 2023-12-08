package dev.siskel.limitedmarket;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
        char what;

        if (e.getClickedBlock() == null) return;

        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign sign = (Sign)e.getClickedBlock().getState();
            if (sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[LimitedMarket]")) {
                if (sign.getLine(1) != "") {
                    what = sign.getLine(1).charAt(0);
                    change = Double.valueOf(sign.getLine(1).substring(1));

                    if (what == '+') {
                        econ.depositPlayer(e.getPlayer(), change);
                        e.getPlayer().sendMessage("You got " + change + " Coin/s!");
                    } else if (what == '-') {
                        econ.withdrawPlayer(e.getPlayer(), change);
                        e.getPlayer().sendMessage("You lost " + change + " Coin/s!");
                    } else {
                        e.getPlayer().sendMessage("You have to Define a + or - at the beginning of the Amount: Invalid -> \"" + sign.getLine(1) + "\"");
                    }
                } else {
                    e.getPlayer().sendMessage("Please Add an Amount to Withdraw or deposit: Invalid -> \"" + sign.getLine(1) + "\"");
                    return;
                }
            }
        }
    }
}
