package me.Skyblueplayer.Game;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Skyblueplayer.Main;
import me.Skyblueplayer.Playerdata.Playermanager;

public class GameManager implements Listener{
	private Main plugin = Main.getPlugin(Main.class);
	
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId(); 
            plugin.playermanager.put(uuid, new Playermanager(uuid, false, false, false, false, false, false));
    }
}
