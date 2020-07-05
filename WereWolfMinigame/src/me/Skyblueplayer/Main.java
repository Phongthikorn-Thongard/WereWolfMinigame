package me.Skyblueplayer;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.Skyblueplayer.Playerdata.Playermanager;

public class Main extends JavaPlugin implements Listener{
	
	
	public HashMap<UUID,Playermanager> playermanager = new HashMap<UUID,Playermanager>();
	
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
