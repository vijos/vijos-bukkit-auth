package org.vijos.auth.lib;

import java.io.IOException;
import java.net.URLEncoder;

import org.bukkit.entity.Player;

import org.vijos.auth.VijosLogin;
import org.vijos.auth.data.Settings;
import org.vijos.auth.data.Sessions;
import org.vijos.auth.data.Messages;
import org.vijos.auth.thread.LoginThread;

public class API {
	
	private static API instance;
	
	private String statusURI;
	private String loginURI;
	
	public static API i() {
		return API.instance;
	}
	
	public API () {
		API.instance = this;
		
		this.statusURI = Settings.i().getString("API.StatusURI");
		this.loginURI = Settings.i().getString("API.LoginURI");
	}
	
	public void login(Player player, String password) {
		if (Sessions.i().getLogin(player)) {
			player.sendMessage(Messages.i().getMessage("Login.In"));
			return;
		}
		
		String playername = player.getName().toLowerCase();
		
		if (Sessions.i().loging.containsKey(playername)) {
			player.sendMessage(Messages.i().getMessage("Login.Ing"));
			return;
		}
		
		new LoginThread(playername, password, VijosLogin.i(), player, true);
		player.sendMessage(Messages.i().getMessage("Login.Start"));
	}
	
	public int getStatus(String username, Player player) {
		int line = 10;
		
		try {
			String dataPost = "username=" + URLEncoder.encode(username, "UTF-8");
			
			line = Integer.parseInt(Sender.Post(this.statusURI, dataPost));
		} catch (IOException exception) {}
		
		return line;
	}

	public int getLogin(String username, String password) {
		int line = 10;
		
		try {
			String dataPost = "username=" + URLEncoder.encode(username, "UTF-8") + "&hash=" + password;
			
			line = Integer.parseInt(Sender.Post(this.loginURI, dataPost));
		} catch (IOException exception) {}
		
		return line;
	}
}
