package com.vijoslogin.lib;

import java.io.IOException;
import java.net.URLEncoder;

import org.bukkit.entity.Player;

import com.vijoslogin.VijosLogin;
import com.vijoslogin.data.ConfigData;
import com.vijoslogin.data.LoginData;
import com.vijoslogin.data.MessageData;
import com.vijoslogin.thread.LoginThread;

public class API {
	
	private static API instance;
	
	private String statusURI;
	private String loginURI;
	
	public static API i() {
		return API.instance;
	}
	
	public API () {
		API.instance = this;
		
		this.statusURI = ConfigData.i().getString("API.StatusURI");
		this.loginURI = ConfigData.i().getString("API.LoginURI");
	}
	
	public void login(Player player, String password) {
		if (LoginData.i().getLogin(player)) {
			player.sendMessage(MessageData.i().getMessage("Login.In"));
			return;
		}
		
		String playername = player.getName().toLowerCase();
		
		if (LoginData.i().loging.containsKey(playername)) {
			player.sendMessage(MessageData.i().getMessage("Login.Ing"));
			return;
		}
		
		new LoginThread(playername, password, VijosLogin.i(), player, true);
		player.sendMessage(MessageData.i().getMessage("Login.Start"));
	}
	
	public int getStatus(String username, Player player) {
		int line = 10;
		
		try {
			String dataPost = "username=" + URLEncoder.encode(username, "UTF-8");
			
			line = Integer.parseInt(URIPost.getPost(this.statusURI, dataPost));
		} catch (IOException exception) {}
		
		return line;
	}

	public int getLogin(String username, String password) {
		int line = 10;
		
		try {
			String dataPost = "username=" + URLEncoder.encode(username, "UTF-8") + "&hash=" + password;
			
			line = Integer.parseInt(URIPost.getPost(this.loginURI, dataPost));
		} catch (IOException exception) {}
		
		return line;
	}
}
