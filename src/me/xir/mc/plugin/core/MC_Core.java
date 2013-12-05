package me.xir.mc.plugin.core;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.xir.mc.plugin.core.MySQL.MySQL;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MC_Core extends JavaPlugin {

	public Logger logger;
	public FileConfiguration config;

	public MC_Core() {
		logger = Logger.getLogger("Minecraft");
	}

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
		logger.info("[MC-Core] has gone offline.");
	}

	public void onEnable() {
		MC_Core_Manager.getInstance().setCore(this);
		loadConfiguration();

		final String host = this.config.getString("mysql.host");
		final String user = this.config.getString("mysql.user");
		final String pass = this.config.getString("mysql.pass");
		final String database = this.config.getString("mysql.database");
		final Integer port = this.config.getInt("mysql.port");

		try {
			MC_Core_Manager.getInstance().setMySQL(new MySQL(host, user, pass, database, port, this));
		} catch (Exception e) {
			MC_Core.this.yFail("FUCKING WINDOWS 98! Why? ", e);
		}

		MC_Core_Manager.getInstance().setServerID(this.config.getInt("general.server-id"));
		logger.info("[MC-Core] is online.");
	}

	public void loadConfiguration() {
		File f = new File(this.getDataFolder(), "config.yml");
		if (!f.exists()) {
			logger.info("[MC-Core] Generating default configuration.");
			saveDefaultConfig();
			config = this.getConfig();
			logger.warning("[MC-Core] URGENT: config.yml has default values!");
		} else {
			logger.info("[MC-Core] Configuration file found.");
			config = this.getConfig();
			int serverId = config.getInt("general.server-id");
			logger.info("This is server ID " + serverId + ".");
		}
	}

}
