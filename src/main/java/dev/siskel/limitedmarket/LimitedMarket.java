package dev.siskel.limitedmarket;

import dev.siskel.limitedmarket.Intenvorys.Events;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class LimitedMarket extends JavaPlugin {

    private static Economy econ = null;
    static ConsoleCommandSender console = Bukkit.getConsoleSender();
    FileConfiguration config = getConfig();


    @Override
    public void onEnable() {
        // Plugin startup logic

        // Disable Plugin when Vault not found
        if (!setupEconomy() ) {
            getLogger().severe("[LimitedMarket] Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Adding Default Configuration and save it to File
        config.addDefault("youAreAwesome", true);
        config.options().copyDefaults(true);
        saveConfig();

        // Registering new Event for Sign Interactions
        Bukkit.getPluginManager().registerEvents(new SignEvents(config), this);
        Bukkit.getPluginManager().registerEvents(new Events(), this);

        // Displaying that the Plugin has Loaded Successfully
        console.sendMessage(ChatColor.GREEN + "[LimitedMarket] Limited Market has been Enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Displaying that the plugin has Disabled Successfully
        console.sendMessage(ChatColor.RED + "[LimitedMarket] Limited Market has been Disabled!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
