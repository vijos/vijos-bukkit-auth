package org.vijos.auth.lib;

import java.util.logging.Logger;

import org.vijos.auth.VijosLogin;

public class ConsoleLogger {
	
	private static ConsoleLogger instance;
	
	private Logger logger;
	
	public static ConsoleLogger i() {
		return ConsoleLogger.instance;
	}
	
	public ConsoleLogger() {
		ConsoleLogger.instance = this;
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