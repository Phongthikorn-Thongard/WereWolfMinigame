package me.Skyblueplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.Skyblueplayer.Game.GameManager;
import me.Skyblueplayer.Game.GameMechanics;
import me.Skyblueplayer.Playerdata.Playermanager;
import me.Skyblueplayer.commands.GameCommands;

public class Main extends JavaPlugin implements Listener{
	
	private static Main instance;
	public HashMap<UUID,Playermanager> playermanager = new HashMap<UUID,Playermanager>();
	public ArrayList<UUID> playersInGame = new ArrayList<>();
	public ArrayList<UUID> randomplayer = new ArrayList<>();
	public ArrayList<UUID> villager = new ArrayList<>();
	public ArrayList<UUID> Ingame = new ArrayList<>();
	
	public GameManager gamemanager;
	public GameMechanics gamemechanics;
	public PlayerScoreboard playerscoreboard;
	
	boolean CanbePlaceblock = false;
	boolean CanbeBreakblock = false;
	
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new GameManager(), this);
		Bukkit.getConsoleSender().sendMessage("§8______________________");
		Bukkit.getConsoleSender().sendMessage("§8|§6	   Werewolf   §8|");
		Bukkit.getConsoleSender().sendMessage("§8|§6  By Skyblueplayer§8|");
		Bukkit.getConsoleSender().sendMessage("§8______________________");
		instanceClasses();
		gamemanager.setUpGame();
		loadConfig();
		setInstance(instance);
		getCommand("werewolfmg").setExecutor(new GameCommands());
		Bukkit.getServer().setDefaultGameMode(GameMode.ADVENTURE);
		for (World world : Bukkit.getServer().getWorlds()) {
			world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
			world.setTime(6000l);
		}
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	private static void setInstance(Main instance) {
		Main.instance = instance;
	}
	
	
	
	
	
	public void loadConfig(){
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	

	public void instanceClasses() {
		gamemanager = new GameManager();
		gamemechanics = new GameMechanics();
		playerscoreboard = new PlayerScoreboard();
	}
	
	
    @EventHandler
    public void placeBlockEvent(BlockPlaceEvent e) {//if Player place block then set cancel like world guard
    	Player p = e.getPlayer();
    	if (getConfig().getBoolean("CanPlaceBlock.boolean") == false) {
        	final String Warnmessage2 = "Don't Place that block.";
        	p.sendMessage(ChatColor.GRAY + Warnmessage2);
    		e.setCancelled(true);
    	}else {
    		p.sendMessage("hi");
    	}
    }
    
    @EventHandler
    public void breakBlockEvent(BlockBreakEvent e) {//if Player break block then set cancel like world guard
    	Player p = e.getPlayer();
    	
    	if(getConfig().getBoolean("CanBreakBlock.boolean") == false) {
        	final String Warnmessage = "Don't Break that block.";
        	p.sendMessage(ChatColor.GRAY + Warnmessage);	
    		e.setCancelled(true);
    	}else {
    		p.sendMessage("hi");
    	}
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	Player p = (Player) sender;
    	if(cmd.getName().equalsIgnoreCase("hello")) {
    		p.teleport(gamemanager.lobbyspawn);
    		p.sendMessage("teleport online we move swifty");
    	}
    	return true;
    }
    
}
