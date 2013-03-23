package com.vijoslogin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMain {
	
	private static CommandMain instance;
	
	private CommandLogout logoutCommand;
	private CommandLogin loginCommand;
	
	public static CommandMain i() {
		return CommandMain.instance;
	}
	
	public CommandMain () {
		CommandMain.instance = this;
		
		this.logoutCommand = new CommandLogout();
		this.loginCommand = new CommandLogin();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		
		if (sender instanceof Player)
			player = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("logout"))
			return logoutCommand.onCommand(sender, player, args);
		if (cmd.getName().equalsIgnoreCase("login"))
			return loginCommand.onCommand(sender, player, args);
		else
			return false;
	}
	
}