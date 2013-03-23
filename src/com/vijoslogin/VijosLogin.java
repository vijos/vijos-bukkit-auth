package com.vijoslogin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.vijoslogin.command.CommandMain;
import com.vijoslogin.data.ConfigData;
import com.vijoslogin.data.MessageData;
import com.vijoslogin.data.LoginData;
import com.vijoslogin.lib.API;
import com.vijoslogin.lib.MsgLogger;
import com.vijoslogin.listener.BlockListener;
import com.vijoslogin.listener.PlayerListener;
import com.vijoslogin.listener.LoginListener;
import com.vijoslogin.lib.MD5;
import com.vijoslogin.lib.URIPost;

public class VijosLogin extends JavaPlugin {
	
	private static VijosLogin instance;
	
	public static VijosLogin i() {
		return VijosLogin.instance;
	}
	
	public void onEnable() {
		VijosLogin.instance = this;
		
		new MD5();
		new URIPost();
		
		new MsgLogger();
		new CommandMain();
		new MessageData();
		new ConfigData();
		new LoginData();
		new API();
		
		PluginManager manager = getServer().getPluginManager();
		
		manager.registerEvents(new PlayerListener(), this);
		manager.registerEvents(new BlockListener(), this);
		manager.registerEvents(new LoginListener(), this);
	}
	
	public void sendLoginMessage(Player player) {
		if (LoginData.i().loging.containsKey(player.getName().toLowerCase())) {
			player.sendMessage(MessageData.i().getMessage("Login.Ing"));
			return;
		}
		player.sendMessage(MessageData.i().getMessage("Login.Tip"));
	}
	
	public void onDisable() {
		MsgLogger.i().info("Plugin disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		return CommandMain.i().onCommand(sender, cmd, commandLabel, args);
	}
	
}