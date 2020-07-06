package me.Skyblueplayer.Game;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Skyblueplayer.Main;
import me.Skyblueplayer.Playerdata.Playermanager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;


public class GameManager implements Listener{
	
	private Main plugin = Main.getPlugin(Main.class);
	
	public boolean canteleport;
	public boolean WarnSetspawnMessage;
	
	public int playerneed = 4;
	
	
	public Location gamespawn;
	public Location lobbyspawn;
	
	private double LobbyX;
	private double LobbyY;
	private double LobbyZ;
	private double GameX, GameY, GameZ;
	String World = "world";
	
	
	public void setUpGame() {
		plugin.getConfig().addDefault("canteleport.boolean", true);
		plugin.getConfig().addDefault("WarnSetspawnMessage.boolean", true);
		plugin.getConfig().addDefault("Lobbyspawn.world", World);
		plugin.getConfig().addDefault("Lobbyspawn.x", LobbyX);
		plugin.getConfig().addDefault("Lobbyspawn.y", LobbyY);
		plugin.getConfig().addDefault("Lobbyspawn.z", LobbyZ);
		plugin.getConfig().addDefault("Gamespawn.world", World);
		plugin.getConfig().addDefault("Gamespawn.x", GameX);
		plugin.getConfig().addDefault("Gamespawn.y", GameY);
		plugin.getConfig().addDefault("Gamespawn.z", GameZ);
		lobbyspawn = new Location(Bukkit.getServer().getWorld(plugin.getConfig().getString("Lobbyspawn.world")),
				plugin.getConfig().getDouble("Lobbyspawn.x"),
				plugin.getConfig().getDouble("Lobbyspawn.y"), 
				plugin.getConfig().getDouble("Lobbyspawn.z"));
		gamespawn = new Location(Bukkit.getServer().getWorld(plugin.getConfig().getString("Gamespawn.world")),
				plugin.getConfig().getDouble("Gamespawn.x"),
				plugin.getConfig().getDouble("Gamespawn.y"), 
				plugin.getConfig().getDouble("Gamespawn.z"));
		
		canteleport = plugin.getConfig().getBoolean("canteleport.boolean");
		WarnSetspawnMessage = plugin.getConfig().getBoolean("WarnSetspawnMessage.boolean");

	}
	
	
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
            Player p = e.getPlayer();
            UUID uuid = p.getUniqueId();
            plugin.playermanager.put(uuid, new Playermanager(uuid, false, false, false, false, false, false));
    		if (WarnSetspawnMessage == true) {
    			TextComponent message = new TextComponent("Don't forget to set Lobbyspawn and GameSpawn location before start game (You can disable this message on config)");
    			message.setColor(ChatColor.RED);
    			message.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, 
    					new ComponentBuilder("You can disable this message on config WarnSetspawnMessage").color(ChatColor.GREEN).create()));
    			for (Player player : Bukkit.getOnlinePlayers()) {
    			    player.spigot().sendMessage(message);
    			}
    		}
    }
    
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {//delete Playermanger when player quit the game
    	Player p = e.getPlayer();
    	UUID uuid= p.getUniqueId();
    	
    	if(plugin.playermanager.containsKey(uuid)) {
    		plugin.playermanager.remove(uuid);
    	}
    }
    
    @EventHandler
    public void foodlevelchangeEvent(FoodLevelChangeEvent e) {
    	e.setCancelled(true);
    }
    
    public void startgame() {
    	//tod
    }

}
