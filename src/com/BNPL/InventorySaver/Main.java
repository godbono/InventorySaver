package com.BNPL.InventorySaver;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main instance;
	protected static Config cf;

	public void onEnable() {
		instance = this;
		(cf = new Config(this, "Setting")).saveDefault();
		getCommand("인벤토리").setExecutor(new Commands());
	}
}
