package me.xir.mc.plugin.core;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.xir.mc.plugin.core.MySQL.MySQL;

import org.bukkit.plugin.java.JavaPlugin;

public class MC_Core extends JavaPlugin {
	
	private final Logger log = Logger.getLogger("Minecraft");
	
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
	
	public void onDisable() {
		log.info("[MC-Core] has gone offline.");
	}

	public void onEnable() {
		MC_Core_Manager.getInstance().setCore(this);
		loadConfiguration();
		
		final String host = this.getConfig().getString("mysql.host");
		final String user = this.getConfig().getString("mysql.user");
		final String pass = this.getConfig().getString("mysql.pass");
		final String database = this.getConfig().getString("mysql.database");
		final Integer port = this.getConfig().getInt("mysql.port");
		
		try {
			MC_Core_Manager.getInstance().setMySQL(new MySQL(host, user, pass, database, port, this));
		} catch (Exception e) {
			MC_Core.this.yFail("FUCKING WINDOWS 98! Why? ", e);
		}
		
		MC_Core_Manager.getInstance().setServerID(this.getConfig().getInt("general.server-id"));
		log.info("[MC-Core] is online.");
	}
	
	public void loadConfiguration() {
		int serverid = this.getConfig().getInt("general.server-id");
		File f = new File(this.getDataFolder(), "config.yml");
		if (!f.exists()) {
			log.info("[MC-Core] Generating default configuration.");
			saveDefaultConfig();
			log.warning("[MC-Core] URGENT: config.yml has default values!");
		} else {
			log.info("[MC-Core] Configuration file found.");
			log.info("This is server ID " + serverid + ".");
		}
	}
	
}
