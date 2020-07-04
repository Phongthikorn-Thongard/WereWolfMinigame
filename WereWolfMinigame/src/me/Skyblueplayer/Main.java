package me.Skyblueplayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("hello")) {
			if (sender instanceof Player) {
				if(p.hasPermission("Lol.use")) {
					p.sendMessage(ChatColor.AQUA + "hello world");
					return false;
				}
			}
		}
		return true;
	}
}
