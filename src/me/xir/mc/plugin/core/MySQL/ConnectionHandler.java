package me.xir.mc.plugin.core.MySQL;

import java.sql.Connection;

import me.xir.mc.plugin.core.MC_Core;

/**
 * Abstract class for base of connection methods
 * @author Cyberpew
 *
 */

public abstract class ConnectionHandler {
	
	protected MC_Core plugin;
	
	protected ConnectionHandler(MC_Core plugin) {
		this.plugin = plugin;
	}
	
	public abstract Connection openConnection();
	
	public abstract boolean checkConnection();
	
	public abstract Connection getConnection();
	
	public abstract void closeConnection();

}
