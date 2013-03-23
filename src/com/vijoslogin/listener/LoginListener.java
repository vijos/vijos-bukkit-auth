package com.vijoslogin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.vijoslogin.VijosLogin;
import com.vijoslogin.data.ConfigData;
import com.vijoslogin.data.LoginData;
import com.vijoslogin.thread.StatusThread;

public class LoginListener implements Listener {
	
	public LoginListener() {
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		
		if (ConfigData.i().getBoolean("Login.AtSpawn")) {
			LoginData.i().locations.put(player.getName().toLowerCase(), player.getLocation());
			player.teleport(player.getWorld().getSpawnLocation());
		}
		
		new StatusThread(player.getName().toLowerCase(), VijosLogin.i(), player);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		
		if (!LoginData.i().getLogin(player) && LoginData.i().locations.containsKey(player.getName().toLowerCase()))
			player.teleport(LoginData.i().locations.get(player.getName().toLowerCase()));
		
		if (LoginData.i().locations.containsKey(player.getName().toLowerCase()))
			LoginData.i().locations.remove(player.getName().toLowerCase());
		
		LoginData.i().delLogin(player);
	}
	
}