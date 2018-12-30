package com.BNPL.InventorySaver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {

	private String path = "plugins\\InventorySaver\\";

	private JavaPlugin plugin;
	private String filename;
	private YamlConfiguration config;
	private File configfile;

	public Config(JavaPlugin plugin, String filename) {
		this.plugin = plugin;
		this.filename = filename;
		reload();
	}

	public void save() {
		if (config != null && configfile != null)
			try {
				config.save(configfile);
			} catch (IOException ex) {
			}
	}

	public void reload() {
		if (configfile == null)
			configfile = new File(path + filename + ".yml");
		config = YamlConfiguration.loadConfiguration(configfile);
		InputStream is = plugin.getResource(filename + ".yml");
		if (is != null)
			config.setDefaults(YamlConfiguration.loadConfiguration(is));
	}

	public void saveDefault() {
		InputStream is = plugin.getResource(filename.replace('\\', '/') + ".yml");
		if (is != null && !isExists()) {
			try {
				String[] cut = filename.split("\\\\");
				File file = new File(path + filename + ".yml");
				File dir = new File(path + filename.replace(cut[cut.length - 1], ""));
				if (!dir.exists())
					dir.mkdirs();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buf = new byte[1024];
				int len;
				while ((len = is.read(buf)) > 0)
					fos.write(buf, 0, len);
				is.close();
				fos.close();
			} catch (Exception ex) {
			}
		}
	}

	public void delete() {
		configfile.delete();
	}

	public boolean isExists() {
		return configfile.exists();
	}

	public int getInt(String key) {
		return config.getInt(key);
	}

	public String getString(String path) {
		return config.getString(path);
	}

	public ItemStack getItemStack(String path) {
		return config.getItemStack(path);
	}

	public List<String> getStringList(String path) {
		return config.getStringList(path);
	}

	public long getLong(String path) {
		return config.getLong(path);
	}

	public boolean getBoolean(String path) {
		return config.getBoolean(path);
	}

	public void createSection(String path) {
		config.createSection(path);
	}

	public ConfigurationSection getConfigurationSection(String path) {
		return config.getConfigurationSection(path);
	}

	public double getDouble(String path) {
		return config.getDouble(path);
	}

	public List<?> getList(String path) {
		return config.getList(path);
	}

	public boolean contains(String path) {
		return config.contains(path);
	}

	public void removeKey(String path) {
		config.set(path, (Object) null);
		save();
	}

	public void set(String path, Object value) {
		config.set(path, value);
		save();
	}

	public Set<String> getKeys(boolean deep) {
		return config.getKeys(deep);
	}

	public boolean isSet(String path) {
		return config.isSet(path);
	}
}
