package com.vijoslogin.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.vijoslogin.VijosLogin;
import com.vijoslogin.data.LoginData;

public class PlayerListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		
		if (player == null)
			return;
		
		if (!LoginData.i().getLogin(event.getPlayer())) {
			
			//Login
			if (event.getMessage().toLowerCase().indexOf("/login") == 0)
				return;
			
			//No chat
			VijosLogin.i().sendLoginMessage(player);
			event.setMessage("/");
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerDropItem(final PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		
		if (player == null)
			return;
		
		if (!LoginData.i().getLogin(player))
			event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (player == null)
			return;
		
		if (!LoginData.i().getLogin(player)) {
			VijosLogin.i().sendLoginMessage(player);
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent event) {	
		/*
		Player player = event.getPlayer();
		if (player == null) return;
		if (LoginData.i().getLogin(player)) return;
		
		VijosLogin.i().sendLoginMessage(player);
		
		if (ConfigData.i().getBoolean("Login.AtSpawn"))
			event.setTo(event.getPlayer().getWorld().getSpawnLocation());
		else
			event.setTo(event.getFrom());
		*/
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		
		if (player == null) return;
		if (LoginData.i().getLogin(player)) return;
		
		VijosLogin.i().sendLoginMessage(player);
		event.getItem().setPickupDelay(30);
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerDamageEvent (EntityDamageEvent event) {
		Entity entity = event.getEntity();
		
		if (entity == null) return;
		if (entity instanceof Player && !LoginData.i().getLogin((Player) entity))
			event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerRespawnEvent (PlayerRespawnEvent event) {
		Entity entity = event.getPlayer();
		if (entity == null) return;
		
		final Player player = (Player) entity;
		if (LoginData.i().getLogin(player))
			return;
		
		VijosLogin.i().getServer().getScheduler().runTaskLaterAsynchronously(VijosLogin.i(), new Runnable() {
			public void run() { player.teleport(player.getWorld().getSpawnLocation()); }
		}, 10L);
	}
}