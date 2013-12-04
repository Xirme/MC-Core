package me.xir.mc.plugin.core;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class MC_Core extends JavaPlugin {
	
	private final Logger log = Logger.getLogger("Minecraft");
	
	public void onDisable() {
		log.info("[MC-Core] has gone offline.");
	}

	public void onEnable() {
		
		log.info("[MC-Core] is online.");
		loadConfiguration();
	}
	
	public void loadConfiguration() {
		int serverid = this.getConfig().getInt("general.server-id");
		File f = new File(this.getDataFolder(), "config.yml");
		if (!f.exists()) {
			log.info("[MC-Core] Generating default configuration.");
			saveDefaultConfig();
			log.info("[MC-Core] URGENT: config.yml has default values!");
		} else {
			log.info("[MC-Core] Configuration file found.");
			log.info("This is server ID " + serverid + ".");
		}
	}
	
}
