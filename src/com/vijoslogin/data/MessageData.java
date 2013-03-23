package com.vijoslogin.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.vijoslogin.VijosLogin;

public class MessageData {
	
	private static MessageData instance;
	
	private YamlConfiguration config;
	private File configFile;
	
	public static MessageData i() {
		return MessageData.instance;
	}
	
	public MessageData() {
		MessageData.instance = this;
		this.configFile = new File(VijosLogin.i().getDataFolder(), "message.yml");
		this.loadConfig();
	}
	
	public void loadConfig() {
		this.config = YamlConfiguration.loadConfiguration(this.configFile);
		this.config.addDefault("Message.Kick", "&a[Vijos]&f 请登录&f");
		this.config.addDefault("Message.Ban", "&a[Vijos]&f You are banned&f");
		this.config.addDefault("Message.UserNotExists", "&a[Vijos]&f 用户不存在，请使用Vijos用户名&f");
		this.config.addDefault("Message.Permission", "&a[Vijos]&f &c您没有权限使用这个命令&f");
		this.config.addDefault("Message.Login.Success", "&a[Vijos]&f &b登录成功&f");
		this.config.addDefault("Message.Login.Start", "&a[Vijos]&f &b登录中&f");
		this.config.addDefault("Message.Login.Ing", "&a[Vijos]&f &c请稍后，正在登录中&f");
		this.config.addDefault("Message.Login.Fail", "&a[Vijos]&f &c用户名或密码错误，登录失败!&f");
		this.config.addDefault("Message.Login.Tip", "&a[Vijos]&f &e请使用 [/login 密码] 登录&f");
		this.config.addDefault("Message.Login.Out", "&a[Vijos]&f &c请在游戏外部登入&f");
		this.config.addDefault("Message.Login.Welcome", "&a[Vijos]&f &e欢迎回来&f");
		this.config.addDefault("Message.Login.In", "&a[Vijos]&f &c您已经登录了&f");
		this.config.addDefault("Message.Logout.Success", "&a[Vijos]&f &b注销成功&f");
		this.config.addDefault("Message.Logout.Fail", "&a[Vijos]&f &c您还没有登录&f");
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
