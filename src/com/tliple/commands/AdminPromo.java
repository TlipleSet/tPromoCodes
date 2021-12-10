package com.tliple.commands;

import com.tliple.base.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class AdminPromo implements CommandExecutor {

    private Main plugin;

    public AdminPromo(Main plugin) {
        this.plugin = plugin;
    }

    private Promocode plugin1;

    public AdminPromo(Promocode plugin) {
        this.plugin1 = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("reload")) {

            if(!sender.hasPermission("pc.reload")) {

                sender.sendMessage("");
                sender.sendMessage(ChatColor.RED + "У вас нет прав для использования этой команды.");
                sender.sendMessage("");
                return true;
            }

            if(args.length == 0) {

                return false;
            }

            if(args.length == 1) {

                String pname = sender.getName();
                String reload = plugin.getConfig().getString("reload_config");

                reload = reload.replace("&", "\u00a7");
                reload = reload.replace("%player%", pname);

                sender.sendMessage(reload);
                plugin.reloadConfig();

                return true;
            }

        }

        if (args[0].equalsIgnoreCase("clearuserdata")) {

            if(!sender.hasPermission("pc.clearuserdata")) {

                sender.sendMessage("");
                sender.sendMessage(ChatColor.RED + "У вас нет прав для использования этой команды.");
                sender.sendMessage("");
                return true;
            }

            if(args.length == 0) {

                return false;
            }

            if(args.length == 1) {

                String pname = sender.getName();
                String clear = plugin.getConfig().getString("clearuserdata");

                if (clear.contains("&"))
                    clear = clear.replace("&", "\u00a7");
                if (clear.contains("%player%"))
                    clear = clear.replace("%player%", pname);

                sender.sendMessage(clear);
                if (plugin1.players.containsKey(player)) {

                    plugin1.players.remove(player);

                    return true;
                }

            }

        }

        return false;

    }

}
