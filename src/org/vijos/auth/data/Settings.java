package org.vijos.auth.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import org.vijos.auth.VijosLogin;

public class Settings {
	
	public static String HashMethod = "md5";
	
	private static Settings instance;
	
	private YamlConfiguration config;
	private File configFile;
	
	public static Settings i() {
		return Settings.instance;
	}
	
	public Settings() {
		Settings.instance = this;
		
		this.configFile = new File(VijosLogin.i().getDataFolder(), "config.yml");
		this.loadConfig();
	}
	
	public void loadConfig() {
		this.config = YamlConfiguration.loadConfiguration(this.configFile);
		this.config.addDefault("Login.AtSpawn", true);
		this.config.addDefault("API.HashMethod", "md5");
		this.config.addDefault("API.CheckCredentials", false);
		this.config.addDefault("API.StatusURI", "https://localhost/status");
		this.config.addDefault("API.LoginURI", "https://localhost/login");
		this.config.options().copyDefaults(true);
		this.saveConfig();
		
		Settings.HashMethod = instance.getString("API.HashMethod").toLowerCase();
	}
	
	public void saveConfig() {
		try {
			this.config.save(configFile);
		} catch (IOException e) {}
	}
	
	public boolean getBoolean(String path) {
		return this.config.getBoolean(path);
	}
	
	public String getString(String path) {
		return this.config.getString(path);
	}
	
}
