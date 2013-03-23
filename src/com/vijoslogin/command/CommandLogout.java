package com.vijoslogin.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vijoslogin.data.ConfigData;
import com.vijoslogin.data.LoginData;
import com.vijoslogin.data.MessageData;

public class CommandLogout {
	
	public boolean onCommand(CommandSender sender, Player player, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player!");
			return false;
		}
		
		if (player == null) {
			sender.sendMessage("You may not do this on the server side!");
			return false;
		}
		
		if (args.length != 0) return false; 
		
		if (LoginData.i().getLogin(sender) == false){
			sender.sendMessage(MessageData.i().getMessage("Logout.Fail"));
			return true;
		}
		
		LoginData.i().delLogin(sender);
		sender.sendMessage(MessageData.i().getMessage("Logout.Success"));
		
		if (ConfigData.i().getBoolean("Login.AtSpawn")) {
			LoginData.i().locations.put(player.getName().toLowerCase(), player.getLocation());
			player.teleport(player.getWorld().getSpawnLocation());
		}
		
		return true;
	}
	
}