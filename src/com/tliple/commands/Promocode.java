package com.tliple.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tliple.base.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Promocode implements CommandExecutor {

    private Main plugin;

    public Promocode(Main plugin) {

        this.plugin = plugin;
    }

    Map<Player, List<String>> players = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String pname = sender.getName();
        Player player = (Player) sender;
        FileConfiguration config = plugin.getConfig();

        for (String promocode : config.getConfigurationSection("promocodes").getKeys(false)) {

            String command = config.getString("promocodes." + promocode + ".command");
            String promo = config.getString("promocodes." + promocode + ".promocode");

            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Данную комманду можно выполнять только игроку!");

                return true;
            }

            if(!sender.hasPermission("pc.promo")) {
                sender.sendMessage("");
                sender.sendMessage(ChatColor.RED + "У вас нет прав для использования этой команды.");
                sender.sendMessage("");

                return true;
            }

            if(args.length == 0) {

                return false;
            }

            if (args[0].equalsIgnoreCase(promo)) {

                command = command.replace("&", "\u00a7");
                command = command.replace("%player%", pname);

                if(players.containsKey(player)) {
                    if (players.get(player).contains(promocode)) {

                        sender.sendMessage("");
                        sender.sendMessage(ChatColor.RED + "Вы уже использованили промокод!");
                        sender.sendMessage("");

                    } else {

                        List<String> players2 = players.get(player);
                        players2.add(promocode);
                        players.put(player, players2);

                        Bukkit.dispatchCommand(sender, command);

                        sender.sendMessage("");
                        sender.sendMessage(ChatColor.YELLOW + "Промокод " + promo + " успешно применён!");
                        sender.sendMessage("");

                    }
                } else {

                    List<String> players2 = new ArrayList<>();
                    players2.add(promocode);
                    players.put(player, players2);

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

                    sender.sendMessage("");
                    sender.sendMessage(ChatColor.YELLOW + "Промокод " + promo + " успешно применён!");
                    sender.sendMessage("");

                }

                return true;

            }

        }

        return false;

    }
}