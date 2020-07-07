package me.Skyblueplayer.random;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Skyblueplayer.Main;
import me.Skyblueplayer.Game.GameMechanics;
import me.Skyblueplayer.Playerdata.Playermanager;
import net.minecraft.server.v1_15_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_15_R1.PlayerConnection;

public class RandomJob {
	
	private Main plugin = Main.getPlugin(Main.class);
	private GameMechanics gamemechanics = new GameMechanics();
	
	public void randomwolf() {
    	Random random = new Random();
    	int index = random.nextInt(plugin.randomplayer.size());
    	Player randomplayer = Bukkit.getPlayer(plugin.randomplayer.get(index));
    	Playermanager canrandom = plugin.playermanager.get(randomplayer.getUniqueId());
    	UUID uuid = randomplayer.getUniqueId();
    	
    	if(canrandom.isHasjob() == false && canrandom.isWolf() == false) {
    		canrandom.setHasjob(true);
    		canrandom.setWolf(true);
    		randomplayer.getInventory().setHelmet(new ItemStack(Material.TNT));//TODO delete this when finish
            randomplayer.updateInventory();
            
    		PacketPlayOutTitle uWolf = new PacketPlayOutTitle(
    				EnumTitleAction.TITLE, ChatSerializer
    					.a("{\"text\":\"YOU ARE: WOLF\",\"color\":\"red\"}"), 20, 40, 30
    				);	

    		PacketPlayOutTitle usubWolf = new PacketPlayOutTitle(
    				EnumTitleAction.SUBTITLE, ChatSerializer
    					.a("{\"text\":\"Kill all players!\",\"color\":\"white\"}"), 20, 40, 30
    				);
    		PlayerConnection connection = ((CraftPlayer)randomplayer).getHandle().playerConnection;
    		connection.sendPacket(uWolf);
    		connection.sendPacket(usubWolf);
            
            gamemechanics.Villageitem(randomplayer);
    		plugin.randomplayer.remove(uuid);
    	}
	}
	
	public void randomwitch() {
    	Random random = new Random();
    	int index = random.nextInt(plugin.randomplayer.size());
    	Player randomplayer = Bukkit.getPlayer(plugin.randomplayer.get(index));
    	Playermanager canrandom = plugin.playermanager.get(randomplayer.getUniqueId());
    	UUID uuid = randomplayer.getUniqueId();
    	
    	if(canrandom.isHasjob() == false && canrandom.isWitch() == false) {
    		canrandom.setHasjob(true);
    		canrandom.setWitch(true);
    		randomplayer.getInventory().setHelmet(new ItemStack(Material.GLASS));//TODO delete this when finish
    		gamemechanics.Villageitem(randomplayer);
            randomplayer.updateInventory();
            
    		PacketPlayOutTitle uSeer = new PacketPlayOutTitle(
    				EnumTitleAction.TITLE, ChatSerializer
    					.a("{\"text\":\"YOU ARE: SEER\",\"color\":\"yellow\"}"), 20, 40, 30
    				);

    		PacketPlayOutTitle usubSeer = new PacketPlayOutTitle(
    				EnumTitleAction.SUBTITLE, ChatSerializer
    					.a("{\"text\":\"You can know When Wolf Transformation\",\"color\":\"White\"}"), 20, 40, 30
    				);
    		PlayerConnection connection = ((CraftPlayer)randomplayer).getHandle().playerConnection;
    		connection.sendPacket(uSeer);
    		connection.sendPacket(usubSeer);
            
    		plugin.randomplayer.remove(uuid);
    	}
	}
	
	public void randomgunner() {
    	Random random = new Random();
    	int index = random.nextInt(plugin.randomplayer.size());
    	Player randomplayer = Bukkit.getPlayer(plugin.randomplayer.get(index));
    	Playermanager canrandom = plugin.playermanager.get(randomplayer.getUniqueId());
    	UUID uuid = randomplayer.getUniqueId();
    	
    	if(canrandom.isHasjob() == false && canrandom.isGunner() == false) {
    		canrandom.setHasjob(true);
    		canrandom.setGunner(true);
    		randomplayer.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK));
    		gamemechanics.Villageitem(randomplayer);
            randomplayer.updateInventory();
            
    		PacketPlayOutTitle uGunner = new PacketPlayOutTitle(
    				EnumTitleAction.TITLE, ChatSerializer
    					.a("{\"text\":\"YOU ARE: GUNNER\",\"color\":\"aqua\"}"), 20, 40, 30
    				);

    		PacketPlayOutTitle usubGunner = new PacketPlayOutTitle(
    				EnumTitleAction.SUBTITLE, ChatSerializer
    					.a("{\"text\":\"Find and Kill The Wolf\",\"color\":\"White\"}"), 20, 40, 30
    				);
    		PlayerConnection connection = ((CraftPlayer)randomplayer).getHandle().playerConnection;
    		connection.sendPacket(uGunner);
    		connection.sendPacket(usubGunner);
    		
    		plugin.villager.add(uuid);
    		plugin.randomplayer.remove(uuid);
            
    		plugin.randomplayer.remove(uuid);
    	}
	}
	
    public void RandomVillage() {
    	Random random = new Random();
    	int index = random.nextInt(plugin.randomplayer.size());
    	Player randomplayer = Bukkit.getPlayer(plugin.randomplayer.get(index));
    	Playermanager canrandom = plugin.playermanager.get(randomplayer.getUniqueId());
    	UUID uuid = randomplayer.getUniqueId();
    	
    	if (canrandom.isHasjob() == false && canrandom.isVillager() == false) {
    		canrandom.setHasjob(true);
    		canrandom.setVillager(true);
    		randomplayer.getInventory().setHelmet(new ItemStack(Material.ANDESITE));//TODO delete this when finish
    		gamemechanics.Villageitem(randomplayer);
    		randomplayer.updateInventory();
    		
    		PacketPlayOutTitle uVillager = new PacketPlayOutTitle(
    				EnumTitleAction.TITLE, ChatSerializer
    					.a("{\"text\":\"YOU ARE: VILLAGER\",\"color\":\"green\"}"), 20, 40, 30
    				);

    		PacketPlayOutTitle usubVillager = new PacketPlayOutTitle(
    				EnumTitleAction.SUBTITLE, ChatSerializer
    					.a("{\"text\":\"Stay alive as long as possible!\",\"color\":\"White\"}"), 20, 40, 30
    				);
    		PlayerConnection connection = ((CraftPlayer)randomplayer).getHandle().playerConnection;
    		connection.sendPacket(uVillager);
    		connection.sendPacket(usubVillager);
    		
    		plugin.villager.add(uuid);
    		plugin.randomplayer.remove(uuid);
    		
    	}
    }
    
	public void randomjob(int online) {
		if (online == 2) {
			randomwolf();
			RandomVillage();
		}
		if (online == 3) {
			randomwolf();
			RandomVillage();
			RandomVillage();
		}
		if (online == 4) {
			randomwolf();
			randomgunner();
			RandomVillage();
			RandomVillage();
		}
		if (online == 5) {
			randomwolf();
			randomgunner();
			randomwitch();
			RandomVillage();//1
			RandomVillage();//2
		}
		if (online == 6) {
			randomwolf();
			randomgunner();
			randomwitch();
			RandomVillage();//1
			RandomVillage();//2
			RandomVillage();//3
		}
		if (online == 7) {
			randomwolf();
			randomgunner();
			randomwitch();
			RandomVillage();//1
			RandomVillage();//2
			RandomVillage();//3
			RandomVillage();//4
		}
		if (online == 8) {
			randomwolf();
			randomgunner();
			randomwitch();
			RandomVillage();//1
			RandomVillage();//2
			RandomVillage();//3
			RandomVillage();//4
			RandomVillage();//5
		}
		if (online == 9) {
			randomwolf();
			randomgunner();
			randomwitch();
			RandomVillage();//1
			RandomVillage();//2
			RandomVillage();//3
			RandomVillage();//4
			RandomVillage();//5
			RandomVillage();//6
		}
		if (online == 10) {
			randomwolf();
			randomgunner();
			randomwitch();
			RandomVillage();//1
			RandomVillage();//2
			RandomVillage();//3
			RandomVillage();//4
			RandomVillage();//5
			RandomVillage();//6
			RandomVillage();//7
		}
		if (online == 11) {
			randomwolf();
			randomgunner();
			randomwitch();
			RandomVillage();//1
			RandomVillage();//2
			RandomVillage();//3
			RandomVillage();//4
			RandomVillage();//5
			RandomVillage();//6
			RandomVillage();//7
			RandomVillage();//8
		}
		if (online == 12) {
			randomwolf();
			randomgunner();
			randomwitch();
			RandomVillage();//1
			RandomVillage();//2
			RandomVillage();//3
			RandomVillage();//4
			RandomVillage();//5
			RandomVillage();//6
			RandomVillage();//7
			RandomVillage();//8
			RandomVillage();//9
		}
	}
}
