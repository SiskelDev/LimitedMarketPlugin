package dev.siskel.limitedmarket;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignEvents implements Listener {


    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if(e.getLine(0).equalsIgnoreCase("[LimitedMarket]")) {
            e.setLine(0, ChatColor.GREEN + "[LimitedMarket]");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;

        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign sign = (Sign)e.getClickedBlock().getState();
            if (sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[LimitedMarket]")) {
                e.getPlayer().sendMessage("Hi there");
            }
        }
    }
}
