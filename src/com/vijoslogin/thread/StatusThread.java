package com.vijoslogin.thread;

import org.bukkit.entity.Player;

import com.vijoslogin.VijosLogin;
import com.vijoslogin.data.MessageData;
import com.vijoslogin.lib.API;

public class StatusThread extends LoginThread {
	
	public StatusThread(String username, VijosLogin plugin, Player player) {
		super(username, null, plugin, player, false);
	}
	
	public void run() {
		if (!this.player.isOnline())
			return;
		
		if (!this.player.hasPermission("vijoslogin.login") && player.isOnline()) {
			player.kickPlayer(MessageData.i().getMessage("Login.Out"));
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
			this.player.kickPlayer(MessageData.i().getMessage("Ban"));
			return false;
		}
		
		this.player.kickPlayer(MessageData.i().getMessage("UserNotExists"));
		return false;
	}
}