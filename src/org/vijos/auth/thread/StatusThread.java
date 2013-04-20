package org.vijos.auth.thread;

import org.bukkit.entity.Player;

import org.vijos.auth.VijosLogin;
import org.vijos.auth.data.Messages;
import org.vijos.auth.lib.API;

public class StatusThread extends LoginThread {
	
	public StatusThread(String username, VijosLogin plugin, Player player) {
		super(username, null, plugin, player, false);
	}
	
	public void run() {
		if (!this.player.isOnline())
			return;
		
		if (!this.player.hasPermission("vijoslogin.login") && player.isOnline()) {
			player.kickPlayer(Messages.i().getMessage("Login.Out"));
		}
		
		if (this.getLogin()) {
			VijosLogin.i().sendLoginMessage(this.player);
		}
	}
	
	public boolean getLogin() {
		int line = API.i().getStatus(this.username, this.player);
		
		if (line == 0)
			return true;
		
		if (line == 1) {
			this.player.kickPlayer(Messages.i().getMessage("Ban"));
			return false;
		}
		
		this.player.kickPlayer(Messages.i().getMessage("UserNotExists"));
		return false;
	}
}