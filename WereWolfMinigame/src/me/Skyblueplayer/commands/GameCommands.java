package me.Skyblueplayer.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Skyblueplayer.Main;
import me.Skyblueplayer.Game.GameManager;
import me.Skyblueplayer.Game.GameMechanics;
import me.Skyblueplayer.random.RandomJob;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;


public class GameCommands implements CommandExecutor{
	private Main plugin = Main.getPlugin(Main.class);
	GameManager g = new GameManager();
	GameMechanics gm = new GameMechanics();
	RandomJob rj = new RandomJob();
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("setlobbyspawn")) {
						plugin.getConfig().addDefault("Lobbyspawn.x", p.getLocation().getX());
						plugin.getConfig().addDefault("Lobbyspawn.y", p.getLocation().getY());
						plugin.getConfig().addDefault("Lobbyspawn.z", p.getLocation().getZ());
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN + "The lobby spawn has been locate");
					}
					else if(args[0].equalsIgnoreCase("setgamespawn")) {
						plugin.getConfig().addDefault("Gamespawn.x", p.getLocation().getX());
						plugin.getConfig().addDefault("Gamespawn.y", p.getLocation().getY());
						plugin.getConfig().addDefault("Gamespawn.z", p.getLocation().getZ());
						plugin.saveConfig();
					}
					else if(args[0].equalsIgnoreCase("start")) {
						if(p.hasPermission("Start.game")) {
						g.Gamestart();
						}
					}
					else if(args[0].equalsIgnoreCase("day")) {
						if(p.hasPermission("Start.game")) {
							gm.switch_night_to_day();
						}
					}
					else if(args[0].equalsIgnoreCase("night")) {
						if(p.hasPermission("Start.game")) {
							
							gm.switch_day_to_night();	
						}
					}
					
					else if(args[0].equalsIgnoreCase("time")) {
						if(p.hasPermission("Start.game")) {
							Bukkit.getServer().broadcastMessage("" + gm.time);
						}
					}
					
				}else {
				p.sendMessage(ChatColor.RED + "Argument is not valid");
				}
			}
		return true;
	}
}
