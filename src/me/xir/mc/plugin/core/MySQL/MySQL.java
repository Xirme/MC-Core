package me.xir.mc.plugin.core.MySQL;

import me.xir.mc.plugin.core.MC_Core;

public class MySQL {
	private MC_Core plugin;
	
	protected String host = plugin.getConfig().getString("mysql.host");
	protected String user = plugin.getConfig().getString("mysql.user");
	protected String pass = plugin.getConfig().getString("mysql.pass");
	protected String database = plugin.getConfig().getString("mysql.database");
	protected Integer port = plugin.getConfig().getInt("mysql.port");

	public MySQL(String host, String user, String pass, String database, Integer port) {
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.database = database;
		this.port = port;
	}
	
	public boolean connect() {

		// connect this shit
		return true;

	}

	public boolean disconnect() {

		// disconnect this shit
		return true;

	}

}
