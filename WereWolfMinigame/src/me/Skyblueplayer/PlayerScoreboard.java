package me.Skyblueplayer;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.Skyblueplayer.Game.GameManager;
import me.Skyblueplayer.Playerdata.Playermanager;


public class PlayerScoreboard {
	
	private GameManager gamemanager = new GameManager();
	private Main plugin = Main.getPlugin(Main.class);
	
    private static ScoreboardManager manager;
    private static Scoreboard board;
    private static Objective objective;
    
    //lobbyscoreboard
    private static Score space;
    private static Score maxplayer;
    private static Score space2;
    private static Score time;
    private static Score space1;
    private static Score credit;
    
    //GameScoreboard
    private static Score playerleft;
    private static Score role;
    private static Score name;
    
    public void scoreGame(Player player, int Playerleft) {
    	
    	UUID uuid = player.getUniqueId();
    	Playermanager playerManager = plugin.playermanager.get(uuid);
    	
    	
    	manager = Bukkit.getScoreboardManager();
    	board = manager.getNewScoreboard();
    	objective = board.registerNewObjective("WereWolf", "dummy", ChatColor.YELLOW  + "" + ChatColor.BOLD + "WEREWOLF");
    	objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    	
    	space = objective.getScore(" ");
    	space.setScore(7);
    	
    	playerleft = objective.getScore("Player left: " + ChatColor.GREEN + Playerleft + "       ");
    	playerleft.setScore(6);
    	
    	space1 = objective.getScore(" ");
    	space1.setScore(5);
    	
    	if (playerManager.isHasjob() == true && playerManager.isVillager()) {
    		role = objective.getScore("Role: " + ChatColor.GREEN + "Villager");
    	}
    	if (playerManager.isHasjob() == true && playerManager.isWolf()) {
    		role = objective.getScore("Role: " + ChatColor.RED + "Wolf");
    	}
    	if (playerManager.isHasjob() == true && playerManager.isWitch()) {
    		role = objective.getScore("Role: " + ChatColor.LIGHT_PURPLE + "Seer");
    	}
    	if (playerManager.isHasjob() == true && playerManager.isGunner()) {
    		role = objective.getScore("Role: " + ChatColor.AQUA + "Gunner");
    	}
    	if (playerManager.isHasjob() == true && playerManager.isIsdead() == true) {
    		role = objective.getScore("Role: " + ChatColor.RED+ "Dead");
    	}
    	role.setScore(4);
    	
    	if (playerManager.isHasjob() == true && playerManager.isVillager()) {
    		name = objective.getScore("Name: " + ChatColor.GREEN + player.getDisplayName());
    	}
    	if (playerManager.isHasjob() == true && playerManager.isWolf()) {
    		name = objective.getScore("Name: " + ChatColor.RED + player.getDisplayName());
    	}
    	if (playerManager.isHasjob() == true && playerManager.isWitch()) {
    		name = objective.getScore("Name: " + ChatColor.LIGHT_PURPLE + player.getDisplayName());
    	}
    	if (playerManager.isHasjob() == true && playerManager.isGunner()) {
    		name = objective.getScore("Name: " + ChatColor.AQUA+ player.getDisplayName());
    	}
    	if (playerManager.isHasjob() == true && playerManager.isIsdead() == true) {
    		name = objective.getScore("Name: " + ChatColor.GREEN + player.getDisplayName());
    	}
    	
    	name.setScore(3);
    	
    	space2 = objective.getScore("                  ");
    	space2.setScore(2);;
    	
    	credit = objective.getScore(ChatColor.YELLOW + "By Skyblueplayer");
    	credit.setScore(1);

    	player.setScoreboard(board);
    }
    
    
    
    public void scorelobby(Player player, int timeleft, int online, int maxplayers) {
    	manager = Bukkit.getScoreboardManager();
    	board = manager.getNewScoreboard();
    	objective = board.registerNewObjective("WereWolf", "dummy", ChatColor.YELLOW  + "" + ChatColor.BOLD + "WEREWOLF");
    	objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    	
    	space1 = objective.getScore("   ");
    	space1.setScore(8);
    	
    	maxplayer = objective.getScore("Players: " + ChatColor.GREEN + online + ChatColor.GREEN +"/" + ChatColor.GREEN + maxplayers);
    	maxplayer.setScore(7);
    	
    	space2 = objective.getScore("  ");
    	space2.setScore(6);
    	
		if (gamemanager.Playercheck(Bukkit.getOnlinePlayers().size()) == false) {
			time = objective.getScore("Wating for player...   ");
		}else {
			time = objective.getScore("Time left: " + ChatColor.GREEN  + timeleft + "     ");
		}
		time.setScore(5);
    	
    	space = objective.getScore(" ");
    	space.setScore(4);

    	credit = objective.getScore(ChatColor.YELLOW + "By Skyblueplayer");
    	credit.setScore(1);
    	player.setScoreboard(board);
    }
}
