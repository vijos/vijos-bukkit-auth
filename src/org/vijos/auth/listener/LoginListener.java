package org.vijos.auth.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import org.vijos.auth.VijosLogin;
import org.vijos.auth.data.Settings;
import org.vijos.auth.data.Sessions;
import org.vijos.auth.thread.StatusThread;

public class LoginListener implements Listener {
	
	public LoginListener() {
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		
		if (Settings.i().getBoolean("Login.AtSpawn")) {
			Sessions.i().locations.put(player.getName().toLowerCase(), player.getLocation());
			player.teleport(player.getWorld().getSpawnLocation());
		}
		
		new StatusThread(player.getName().toLowerCase(), VijosLogin.i(), player);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		
		if (!Sessions.i().getLogin(player) && Sessions.i().locations.containsKey(player.getName().toLowerCase()))
			player.teleport(Sessions.i().locations.get(player.getName().toLowerCase()));
		
		if (Sessions.i().locations.containsKey(player.getName().toLowerCase()))
			Sessions.i().locations.remove(player.getName().toLowerCase());
		
		Sessions.i().delLogin(player);
	}
	
}