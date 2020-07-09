package me.Skyblueplayer.Game;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.Skyblueplayer.Main;

public class GameMechanics{
	
	public long time = 6000l;	
	
	private Main plugin = Main.getPlugin(Main.class);

	public void Villageitem(Player player) {
		player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		player.updateInventory();
	}
	
	public void switch_night_to_day() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (World world : Bukkit.getServer().getWorlds()) {
					
					if (time != 6000l) {
						time = time + 100l;
						world.setTime(time);
						if(time > 24000l) {
							time = 0l;
						}
						Bukkit.getServer().broadcastMessage("" + time);
					}
					else if (time == 6000l) {
						this.cancel();
					}
				}
			}
		}.runTaskTimer(plugin, 0, 1l);
	}
	
	public void switch_day_to_night() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (World world : Bukkit.getServer().getWorlds()) {
					
					if (time != 18000l) {
						time = time + 100l;
						world.setTime(time);
						
						if(time > 24000l) {
							time = 0l;
						}
						Bukkit.getServer().broadcastMessage("" + time);
					}
					else if (time == 18000l) {
						this.cancel();
					}
				}
			}
		}.runTaskTimer(plugin, 0, 1l);
	}
	
	
	
}
