package me.xir.mc.plugin.core;

public class MC_Core_Manager {
	
	private MC_Core core;
	private int serverID;
	
	public void setCore(MC_Core core) {
		this.core = core;
	}
	
	public void setServerID(int id) {
		this.serverID = id;
	}
	
	private static MC_Core_Manager self = new MC_Core_Manager();
	
	public static MC_Core getCore() {
		return MC_Core_Manager.getInstance().core;
	}
	
	public static MC_Core_Manager getInstance() {
		return MC_Core_Manager.self;
	}

	public static int getServerID() {
		return MC_Core_Manager.getInstance().serverID;
	}
	
}
