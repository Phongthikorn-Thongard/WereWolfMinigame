package me.Skyblueplayer.Game;


import java.util.UUID;

//test
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
import me.Skyblueplayer.random.RandomJob;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;


public class GameManager implements Listener{
	
	private Main plugin = Main.getPlugin(Main.class);
	private RandomJob randomjob = new RandomJob();
	
	public boolean canteleport;
	public boolean WarnSetspawnMessage;
	
	public int playerneed = 2; //TODO need 4 player
	public int lobbycountdown = 60;
	public int maxplayer = 12;
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
		updatescore();
		Playercheck(Bukkit.getOnlinePlayers().size());
		canteleport = plugin.getConfig().getBoolean("canteleport.boolean");
		WarnSetspawnMessage = plugin.getConfig().getBoolean("WarnSetspawnMessage.boolean");

	}
	
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
            Player p = e.getPlayer();
            UUID uuid = p.getUniqueId();
            plugin.playermanager.put(uuid, new Playermanager(uuid, false, false, false, false, false, false, false));
    		plugin.playersInGame.add(uuid);
    		plugin.randomplayer.add(uuid);

    		if (WarnSetspawnMessage == true) {
    			TextComponent message = new TextComponent("Don't forget to set Lobbyspawn and GameSpawn location before start game (You can disable this message on config)");
    			message.setColor(ChatColor.RED);
    			message.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, 
    					new ComponentBuilder("You can disable this message on config WarnSetspawnMessage").color(ChatColor.GREEN).create()));
    			for (Player player : Bukkit.getOnlinePlayers()) {
    			    player.spigot().sendMessage(message);
    			}
    		}
    			
    		Bukkit.getOnlinePlayers().forEach(online -> plugin.playerscoreboard.scorelobby(online, lobbycountdown ,
    				Bukkit.getOnlinePlayers().size(), maxplayer));
    		
    		if (Bukkit.getOnlinePlayers().size() <= playerneed - 1 && gamestart == false) {
    			Bukkit.getServer().broadcastMessage(ChatColor.RED + "Need " + ChatColor.RED + remainplayer(Bukkit.getOnlinePlayers()
    					.size(), playerneed) + ChatColor.RED + " More Player to start game");
    		}
    		
    		
    }
    
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {//delete Playermanger when player quit the game
    	Player p = e.getPlayer();
    	UUID uuid= p.getUniqueId();
    	
		if (Bukkit.getOnlinePlayers().size() <= playerneed - 1 && gamestart == false) {
			Bukkit.getServer().broadcastMessage(ChatColor.RED + "Need " + ChatColor.RED + remainplayer(Bukkit.getOnlinePlayers()
					.size(), playerneed) + ChatColor.RED + " More Player to start game");
		}
    	
    	if (plugin.playermanager.containsKey(uuid) && plugin.playersInGame.contains(uuid)) {
    		plugin.playermanager.remove(uuid);
    		plugin.playersInGame.remove(uuid);
    		plugin.randomplayer.remove(uuid);
    		plugin.Ingame.remove(uuid);
    	}
    	
    	
    	//Bukkit.getOnlinePlayers().forEach(onlineingame -> plugin.playerscoreboard.scoreGame(onlineingame, 
    			//plugin.Ingame.size()));
    	
    	
    	
    }
    
    @EventHandler
    public void foodlevelchangeEvent(FoodLevelChangeEvent e) {
    	e.setCancelled(true);
    }
    
    public int remainplayer(int online, int playerneed) {
    	int result = playerneed - online;
    	return result;	
    }
    
    public boolean Playercheck(int player) {
    	if (player >= playerneed) {// return true
    		return true;
    	}else {
    		return false;
        }
    }
    
    int PlayerIngame = plugin.Ingame.size();
    public void Gamestart() {
    	if (gamestart == false) {
    		randomjob.randomjob(Bukkit.getOnlinePlayers().size());
    	}
    	Bukkit.getOnlinePlayers().forEach(player -> {
        	UUID uuid = player.getUniqueId();
        	setUpGame();
			player.teleport(gamespawn);
			player.getInventory().clear();
			if (gamestart == false) {
				plugin.Ingame.add(uuid);
			}
    		plugin.playerscoreboard.scoreGame(player, plugin.Ingame.size());//TODO
    	});
    }
    

    public void lobbycountdown() {
    	new BukkitRunnable() {
			
			@Override
			public void run() {
			if (gamestart == false) {
				if (lobbycountdown == 0) {
				
						Gamestart();
						gamestart = true;
				}
			}
			
			if (gamestart == false) {
				if (lobbycountdown > 0) {
					if (Playercheck(Bukkit.getOnlinePlayers().size())) {
						if (lobbycountdown == 60) {
							Bukkit.getServer().broadcastMessage("§eThe game will start in §f" + lobbycountdown + " seconds");
							for (Player online : Bukkit.getOnlinePlayers()) {
	                            online.playSound(online.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
								}
						}
						lobbycountdown--;
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
							
						}else {
							lobbycountdown = 60;
							}
						}
					}
				}
		}.runTaskTimer(plugin, 0, 20l);
    }
    
    public void updatescore() {//update scoreboard
    	new BukkitRunnable() {
			
			@Override
			public void run() {
				if (gamestart == false) {
					Bukkit.getOnlinePlayers().forEach(online -> plugin.playerscoreboard.scorelobby(online, lobbycountdown ,
							Bukkit.getOnlinePlayers().size(), maxplayer));
				}
				if (gamestart == true) {
					Bukkit.getOnlinePlayers().forEach(onlineingame -> plugin.playerscoreboard.scoreGame(
							onlineingame, plugin.Ingame.size()));
				}
			}
		}.runTaskTimer(plugin, 0, 20l);
    }
    
	public void setGamestart(boolean gamestart) {
		this.gamestart = gamestart;
	}

}
