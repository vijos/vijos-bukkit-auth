package org.vijos.auth.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.vijos.auth.data.Settings;
import org.vijos.auth.data.Sessions;
import org.vijos.auth.data.Messages;

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
		
		if (Sessions.i().getLogin(sender) == false){
			sender.sendMessage(Messages.i().getMessage("Logout.Fail"));
			return true;
		}
		
		Sessions.i().delLogin(sender);
		sender.sendMessage(Messages.i().getMessage("Logout.Success"));
		
		if (Settings.i().getBoolean("Login.AtSpawn")) {
			Sessions.i().locations.put(player.getName().toLowerCase(), player.getLocation());
			player.teleport(player.getWorld().getSpawnLocation());
		}
		
		return true;
	}
	
}