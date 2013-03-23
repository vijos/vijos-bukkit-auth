package com.vijoslogin.lib;

import java.util.logging.Logger;

import com.vijoslogin.VijosLogin;

public class MsgLogger {
	
	private static MsgLogger instance;
	
	private Logger logger;
	
	public static MsgLogger i() {
		return MsgLogger.instance;
	}
	
	public MsgLogger() {
		MsgLogger.instance = this;
		this.logger = VijosLogin.i().getLogger();
	}
	
	public void info(String Message) {
		this.logger.info(Message);
	}
	
	public void warning(String Message) {
		this.logger.warning("=====================[VijosLogin]=====================");
		this.logger.warning(Message);
		this.logger.warning("=====================================================");
	}
	
}