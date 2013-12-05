package me.xir.mc.plugin.core.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import me.xir.mc.plugin.core.MC_Core;

public class MySQL {
	private MC_Core plugin;
	private Connector connector;
	
	protected String host;
	protected String user;
	protected String pass;
	protected String database;
	protected Integer port;

	public MySQL(String host, String user, String pass, String database, Integer port, MC_Core plugin) throws ClassNotFoundException, SQLException {
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.database = database;
		this.port = port;
		this.plugin = plugin;
		
		Class.forName("com.mysql.jdbc.Driver");
		connector = new Connector("jdbc:mysql//" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true&user=" + this.user + "&password=" + this.pass);
		
		this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				connector.resetLoad();
			}
		}, 100, 100);
	}
	
	public PreparedStatement getPreparedStatementFromOven(String query) throws SQLException {
		return connector.getConnection().prepareStatement(query);
	}
	
	public PreparedStatement getPreparedStatementsKeys(String query) throws SQLException {
		return connector.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	}
	
	public Connection getConnection() throws SQLException {
		return connector.getConnection();
	}
	
	public Connector getPool() {
		return connector;
	}
}