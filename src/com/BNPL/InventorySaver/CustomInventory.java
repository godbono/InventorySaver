package com.BNPL.InventorySaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CustomInventory {

	private String name;
	private Config cf;

	public CustomInventory(String name) {
		this.name = name;
		this.cf = new Config(Main.instance, "인벤토리\\" + name);
	}

	public void save(PlayerInventory inv) {
		for (int i = 0; i < 40; i++)
			setItem(i, inv.getItem(i));
	}

	public void load(Player player) {
		PlayerInventory inv = player.getInventory();
		for (int i = 0; i < 40; i++)
			inv.setItem(i, getItem(i));
	}

	public void delete() {
		new File("plugins\\InventorySaver\\인벤토리\\" + name).delete();
	}

	public void setItem(int slot, ItemStack is) {
		cf.set("인벤토리." + slot, is);
	}

	public ItemStack getItem(int slot) {
		return cf.getItemStack("인벤토리." + slot);
	}

	public String getName() {
		return this.name;
	}

	public static List<String> getInventoryList() {
		List<String> l = new ArrayList<>();
		File dir = new File("plugins\\InventorySaver\\인벤토리\\");
		File[] files = dir.listFiles();
		if (files == null)
			return l;
		for (File f : files)
			l.add(f.getName().replace(".yml", ""));
		return l;
	}

	public static boolean containsInventory(String inv) {
		return getInventoryList().contains(inv);
	}

}
