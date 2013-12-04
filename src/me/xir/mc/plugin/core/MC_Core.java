package me.xir.mc.plugin.core;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class MC_Core extends JavaPlugin {
	
	private final Logger log = Logger.getLogger("Minecraft");
	
	public void onDisable() {
		log.info("[MC-Core] is online.");
	}

	public void onEnable() {
		log.info("[MC-Core] has gone offline.");
	}
	
}
