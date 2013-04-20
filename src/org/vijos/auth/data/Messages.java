package org.vijos.auth.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import org.vijos.auth.VijosLogin;

public class Messages {
	
	private static Messages instance;
	
	private YamlConfiguration config;
	private File configFile;
	
	public static Messages i() {
		return Messages.instance;
	}
	
	public Messages() {
		Messages.instance = this;
		this.configFile = new File(VijosLogin.i().getDataFolder(), "message.yml");
		this.loadConfig();
	}
	
	public void loadConfig() {
		this.config = YamlConfiguration.loadConfiguration(this.configFile);
		this.config.addDefault("Message.Kick", "&a[Vijos]&f Please log in to continue.&f");
		this.config.addDefault("Message.Ban", "&a[Vijos]&f You are banned.&f");
		this.config.addDefault("Message.UserNotExists", "&a[Vijos]&f User isn't existed! Please use Vijos username.&f");
		this.config.addDefault("Message.Permission", "&a[Vijos]&f &cYou havn't the permission to run this command.&f");
		this.config.addDefault("Message.Login.Success", "&a[Vijos]&f &bLogin successfully.&f");
		this.config.addDefault("Message.Login.Start", "&a[Vijos]&f &bLogin in progress...&f");
		this.config.addDefault("Message.Login.Ing", "&a[Vijos]&f &cPlease wait...&f");
		this.config.addDefault("Message.Login.Fail", "&a[Vijos]&f &cWrong username or password!&f");
		this.config.addDefault("Message.Login.Tip", "&a[Vijos]&f &ePlease use [/login password] to login&f");
		this.config.addDefault("Message.Login.Welcome", "&a[Vijos]&f &eWelcome back!&f");
		this.config.addDefault("Message.Login.In", "&a[Vijos]&f &cYou have already logged in.&f");
		this.config.addDefault("Message.Logout.Success", "&a[Vijos]&f &bLogout successfully.&f");
		this.config.addDefault("Message.Logout.Fail", "&a[Vijos]&f &cYou haven't logged in.&f");
		this.config.options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void saveConfig() {
		try {
			this.config.save(this.configFile);
		} catch (IOException e) {}
	}
	
	public String getMessage(String action) {
		return this.config.getString("Message." + action).replaceAll("(&([a-f0-9]))", "\u00A7$2");
	}
	
}
