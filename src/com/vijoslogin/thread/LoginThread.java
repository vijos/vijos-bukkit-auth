package com.vijoslogin.thread;

import org.bukkit.entity.Player;

import com.vijoslogin.VijosLogin;
import com.vijoslogin.data.LoginData;
import com.vijoslogin.data.MessageData;
import com.vijoslogin.lib.MD5;
import com.vijoslogin.lib.MsgLogger;
import com.vijoslogin.lib.API;

public class LoginThread extends Thread {
	
	public String username;
	public String password;
	public VijosLogin plugin;
	public Player player;
	
	public LoginThread(String username, String password, VijosLogin plugin, Player player, Boolean putLoging) {
		this.username = username;
		this.plugin = plugin;
		this.player = player;
		
		if (password != null)
			this.password = MD5.md5(password);
		else
			this.password = password;
		
		//if (putLoging)
		//	LoginData.i().loging.put(this.username, this);
		
		this.start();
	}
	
	public void run() {
		if (!this.player.isOnline())
			return;
		
		if (this.getLogin()) {
			LoginData.i().setLogin(this.player);
			this.player.sendMessage(MessageData.i().getMessage("Login.Success"));
			MsgLogger.i().info(player.getName() + "[" + player.getAddress().getAddress().getHostAddress() + "] logged in");
		}
	}
	
	public boolean getLogin() {
		int line = API.i().getLogin(this.username, this.password);
		
		if (line == 0) {
			if (LoginData.i().locations.containsKey(this.username))
				player.teleport(LoginData.i().locations.get(username));
			
			return true;
		}
		
		this.player.sendMessage(MessageData.i().getMessage("Login.Fail"));
		return false;
	}
}