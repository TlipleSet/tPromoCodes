package com.tliple.base;

import com.tliple.commands.AdminPromo;
import com.tliple.commands.Promocode;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.event.TextEvent;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {

        log.info("Plugin enabled!");

        getServer().getPluginCommand("promocode").setExecutor(new Promocode(this));
        getServer().getPluginCommand("admin_promo").setExecutor(new AdminPromo(this));

        saveDefaultConfig();

    }

    @Override
    public void onDisable() {

        log.info("Plugin disabled!");

    }

}
