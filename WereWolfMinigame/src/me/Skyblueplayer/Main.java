package me.Skyblueplayer;

import org.bukkit.Bukkit;
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
		Bukkit.getConsoleSender().sendMessage("§8______________________");
		Bukkit.getConsoleSender().sendMessage("§8|§6	   Werewolf   §8|");
		Bukkit.getConsoleSender().sendMessage("§8|§6  By Skyblueplayer§8|");
		Bukkit.getConsoleSender().sendMessage("§8______________________");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {//Command
		return true;
	}
}
