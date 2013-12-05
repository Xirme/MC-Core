package me.xir.mc.plugin.core;

import java.io.File;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.xir.mc.plugin.core.MySQL.MySQL;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MC_Core extends JavaPlugin {
	
	public final Logger log = Logger.getLogger("Minecraft");
	public FileConfiguration config;
	public Integer serverid;
	
	public void yFail(String reason) {
		this.yFail(reason, null);
	}
	
	public void yFail(String reason, Exception exception) {
		if (exception != null) {
			this.getLogger().log(Level.SEVERE, "Shutdown caused by: " + reason, exception);
		} else {
			this.getLogger().severe("Shutdown caused by: " + reason);
		}
		this.getServer().getPluginManager().disablePlugin(this);
	}
	
	final String host = this.config.getString("mysql.host");
	final String user = this.config.getString("mysql.user");
	final String password = this.config.getString("mysql.pass");
	final String database = this.config.getString("mysql.database");
	final Integer port = this.config.getInt("mysql.port");
	
	MySQL MySQL = new MySQL(this, host, port, database, user, password);
	Connection conn  = null;
	
	public void onDisable() {
		log.info("[MC-Core] has gone offline.");
	}

	public void onEnable() {
		MC_Core_Manager.getInstance().setCore(this);
		loadConfiguration();
		
		try {
			conn = MySQL.openConnection();
		} catch (Exception ex) {
			this.yFail("SQL dun goof'd. \n\n", ex);
		}
		
		MC_Core_Manager.getInstance().setServerID(this.serverid);
		log.info("[MC-Core] is online.");
		
		if (MySQL.checkConnection() != true) {
				log.info("Connection to MySQL was successful.");
		} else {
			log.warning("Something went wrong with MySQL.");
		}
	}
	
	public void loadConfiguration() {
		File f = new File(this.getDataFolder(), "config.yml");
		if (!f.exists()) {
			log.info("[MC-Core] Generating default configuration.");
			saveDefaultConfig();
			this.config = this.getConfig();
			log.warning("[MC-Core] URGENT: config.yml has default values!");
		} else {
			log.info("[MC-Core] Configuration file found.");
			this.config = this.getConfig();
			this.serverid = this.config.getInt("general.server-id");
			log.info("This server's ID is " + serverid + ".");
		}
	}
	
}
