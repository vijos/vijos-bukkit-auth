package org.vijos.auth.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import org.vijos.auth.VijosLogin;
import org.vijos.auth.data.Sessions;

public class BlockListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (player == null) return;
		if (!Sessions.i().getLogin(player)) {
			VijosLogin.i().sendLoginMessage(player);
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (player == null) return;
		if (!Sessions.i().getLogin(player)) {
			VijosLogin.i().sendLoginMessage(player);
			event.setCancelled(true);
		}
	}
}