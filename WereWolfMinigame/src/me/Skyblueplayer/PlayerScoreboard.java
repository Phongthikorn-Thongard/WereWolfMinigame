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
    private static Score time;
    
    
    public void scorelobby(Player player, int timeleft) {
    	manager = Bukkit.getScoreboardManager();
    	board = manager.getNewScoreboard();
    	objective = board.registerNewObjective("WereWolf", "");
    	objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    	objective.setDisplayName(ChatColor.GOLD + "WEREWOLF");
    	
    	time = objective.getScore(ChatColor.AQUA + "Time " + timeleft);
    	time.setScore(4);
    	if (gamemanager.Playercheck(Bukkit.getOnlinePlayers().size()) == false) {
    		
    	}
    	player.setScoreboard(board);
    }
}
