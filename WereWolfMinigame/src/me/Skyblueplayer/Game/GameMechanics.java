package me.Skyblueplayer.Game;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameMechanics{	
	
	public void Villageitem(Player player) {
		player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		player.updateInventory();
	}
	
}
