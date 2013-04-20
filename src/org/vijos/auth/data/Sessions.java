package org.vijos.auth.data;

import java.util.Hashtable;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.vijos.auth.thread.LoginThread;

public class Sessions {
	
	private static Sessions instance;
	
	public Hashtable<String, Long> loginState;
	public Hashtable<String, Location> locations;
	public Hashtable<String, LoginThread> loging;
	
	public static Sessions i() {
		return Sessions.instance;
	}
	
	public Sessions() {
		Sessions.instance = this;
		
		this.loginState = new Hashtable<String, Long>();
		this.locations = new Hashtable<String, Location>();
		this.loging = new Hashtable<String, LoginThread>();
	}
	
	public void setLogin(Player player) {
		this.loginState.put(player.getPlayer().getName().toLowerCase(), System.currentTimeMillis());
	}
	
	public void setLogin(CommandSender sender) {
		Player player = (Player) sender;
		this.setLogin(player);
	}
	
	public void delLogin(Player player) {
		this.delLogin(player.getPlayer().getName().toLowerCase());
	}
	
	public void delLogin(String player) {
		if (this.loginState.containsKey(player))
			this.loginState.remove(player);
	}
	
	public void delLogin(CommandSender sender) {
		this.delLogin(sender.getName().toLowerCase());
	}
	
	public boolean getLogin(Player player) {
		return this.getLogin(player.getPlayer().getName().toLowerCase());
	}
	
	public boolean getLogin(String player) {
		if (!this.loginState.containsKey(player))
			return false;
		
		return true;
	}
	
	public boolean getLogin(CommandSender sender) {
		return this.getLogin((Player) sender);
	}
}
