package me.Skyblueplayer.Game;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
	public int lobbycountdown = 60;
	public boolean gamestart = false;
	
	public Location gamespawn;
	public Location lobbyspawn;
	
	private double LobbyX;
	private double LobbyY;
	private double LobbyZ;
	private double GameX, GameY, GameZ;
	String World = "world";
	String World2 = "world";
	
	
	public void setUpGame() {
		plugin.getConfig().addDefault("canteleport.boolean", true);
		plugin.getConfig().addDefault("WarnSetspawnMessage.boolean", true);
		plugin.getConfig().addDefault("Lobbyspawn.world", World);
		plugin.getConfig().addDefault("Lobbyspawn.x", LobbyX);
		plugin.getConfig().addDefault("Lobbyspawn.y", LobbyY);
		plugin.getConfig().addDefault("Lobbyspawn.z", LobbyZ);
		plugin.getConfig().addDefault("Gamespawn.world", World2);
		plugin.getConfig().addDefault("Gamespawn.x", GameX);
		plugin.getConfig().addDefault("Gamespawn.y", GameY);
		plugin.getConfig().addDefault("Gamespawn.z", GameZ);
		this.lobbyspawn = new Location(Bukkit.getServer().getWorld(plugin.getConfig().getString("Lobbyspawn.world")),
				plugin.getConfig().getDouble("Lobbyspawn.x"),
				plugin.getConfig().getDouble("Lobbyspawn.y"), 
				plugin.getConfig().getDouble("Lobbyspawn.z"));
		this.gamespawn = new Location(Bukkit.getServer().getWorld(plugin.getConfig().getString("Gamespawn.world")),
				plugin.getConfig().getDouble("Gamespawn.x"),
				plugin.getConfig().getDouble("Gamespawn.y"), 
				plugin.getConfig().getDouble("Gamespawn.z"));
		lobbycountdown();
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
    		Bukkit.getOnlinePlayers().forEach(online -> plugin.playerscoreboard.scorelobby(online, lobbycountdown));
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
    	gamestart = true;
    	setUpGame(); //get coordinate form config
    	 for (Player online : Bukkit.getOnlinePlayers()) {
    		online.getInventory().clear();
    		online.teleport(gamespawn);
    	 }
    	
    }
    
    public boolean Playercheck(int player) {
    	if (player >= playerneed) {// return true
    		return true;
    	}else {
    		return false;
        }
    }
    

    
    public void lobbycountdown() {
    	new BukkitRunnable() {
			
			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				if (lobbycountdown > 0) {
						lobbycountdown--;
						Bukkit.getOnlinePlayers().forEach(online -> plugin.playerscoreboard.scorelobby(online, lobbycountdown));
						if(lobbycountdown == 30) {
							Bukkit.getServer().broadcastMessage("§eThe game will start in §f" + lobbycountdown + " seconds");
	                        for (Player online : Bukkit.getOnlinePlayers()) {
	                            online.playSound(online.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
	                        }
						}
						if(lobbycountdown == 15) {
							Bukkit.getServer().broadcastMessage("§eThe game will start in §f" + lobbycountdown + " seconds");
	                        for (Player online : Bukkit.getOnlinePlayers()) {
	                            online.playSound(online.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
	                        }
						}else if (lobbycountdown == 10) {
							Bukkit.getServer().broadcastMessage("§eThe game will start in §f" + lobbycountdown + " seconds");
							for (Player online : Bukkit.getOnlinePlayers()) {
	                            online.playSound(online.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
	                        }
						}
						else if (lobbycountdown <= 5) {
							Bukkit.getServer().broadcastMessage("§eThe game will start in §f" + lobbycountdown + " seconds");
							for (Player online : Bukkit.getOnlinePlayers()) {
	                            online.playSound(online.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
	                        }
						}
					}
				}
		}.runTaskTimer(plugin, 0, 20l);
    }

}
