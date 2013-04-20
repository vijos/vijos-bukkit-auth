package org.vijos.auth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.vijos.auth.command.CommandMain;
import org.vijos.auth.data.Settings;
import org.vijos.auth.data.Messages;
import org.vijos.auth.data.Sessions;
import org.vijos.auth.lib.API;
import org.vijos.auth.lib.ConsoleLogger;
import org.vijos.auth.listener.BlockListener;
import org.vijos.auth.listener.PlayerListener;
import org.vijos.auth.listener.LoginListener;
import org.vijos.auth.lib.Hash;
import org.vijos.auth.lib.Sender;

public class VijosLogin extends JavaPlugin {
	
	private static VijosLogin instance;
	
	public static VijosLogin i() {
		return VijosLogin.instance;
	}
	
	public void onEnable() {
		VijosLogin.instance = this;
		
		new Settings();
		new API();
		new ConsoleLogger();
		new Hash();
		new Sender();
		new CommandMain();
		new Messages();
		new Sessions();
		
		PluginManager manager = getServer().getPluginManager();
		
		manager.registerEvents(new PlayerListener(), this);
		manager.registerEvents(new BlockListener(), this);
		manager.registerEvents(new LoginListener(), this);
	}
	
	public void sendLoginMessage(Player player) {
		if (Sessions.i().loging.containsKey(player.getName().toLowerCase())) {
			player.sendMessage(Messages.i().getMessage("Login.Ing"));
			return;
		}
		player.sendMessage(Messages.i().getMessage("Login.Tip"));
	}
	
	public void onDisable() {
		ConsoleLogger.i().info("Plugin disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		return CommandMain.i().onCommand(sender, cmd, commandLabel, args);
	}
	
}