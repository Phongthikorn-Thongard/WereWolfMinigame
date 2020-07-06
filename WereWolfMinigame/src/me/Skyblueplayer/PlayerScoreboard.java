package me.Skyblueplayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.Skyblueplayer.Game.GameManager;


public class PlayerScoreboard {
	GameManager gamemanager = new GameManager();
    private static ScoreboardManager manager;
    private static Scoreboard board;
    private static Objective objective;
    private static Score space;
    private static Score maxplayer;
    private static Score space2;
    private static Score time;
    private static Score space1;
    private static Score credit;
    
    
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
