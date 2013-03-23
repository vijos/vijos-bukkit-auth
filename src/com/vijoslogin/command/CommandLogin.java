package com.vijoslogin.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vijoslogin.VijosLogin;
import com.vijoslogin.lib.API;

public class CommandLogin {
	
	public boolean onCommand(CommandSender sender, Player player, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player!");
			return false;
		}
		
		if (player == null) {
			sender.sendMessage("You may not do this on the server side!");
			return false;
		}
		
		if (args.length != 1) return false; 
		
		String password = args[0];
		
		if (password == null || password.trim().equals("")) {
			VijosLogin.i().sendLoginMessage(player);
			return false;
		}
		
		API.i().login(player, password);
		
		return true;
	}
	
}