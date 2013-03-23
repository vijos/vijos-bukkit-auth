package com.vijoslogin.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.vijoslogin.VijosLogin;

public class ConfigData {
	
	private static ConfigData instance;
	
	private YamlConfiguration config;
	private File configFile;
	
	public static ConfigData i() {
		return ConfigData.instance;
	}
	
	public ConfigData() {
		ConfigData.instance = this;
		
		this.configFile = new File(VijosLogin.i().getDataFolder(), "config.yml");
		this.loadConfig();
	}
	
	public void loadConfig() {
		this.config = YamlConfiguration.loadConfiguration(this.configFile);
		this.config.addDefault("Login.AtSpawn", true);
		this.config.addDefault("Login.Chat", false);
		this.config.addDefault("API.StatusURI", "https://localhost/status");
		this.config.addDefault("API.LoginURI", "https://localhost/login");
		this.config.options().copyDefaults(true);
		this.saveConfig();
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
