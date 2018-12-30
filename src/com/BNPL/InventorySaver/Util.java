package com.BNPL.InventorySaver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {

	public static String prefix = replaceColor(new Config(Main.instance, "Setting").getString("羨砧紫"));

	protected static void log(String text) {
		Bukkit.getConsoleSender().sendMessage(prefix + text);
	}

	protected static void nplog(String text) {
		Bukkit.getConsoleSender().sendMessage(text);
	}

	protected static void sendMessage(CommandSender sender, String text) {
		if (sender != null)
			sender.sendMessage(prefix + text);
	}

	protected static void sendMessage(CommandSender sender, String text, boolean isprefix) {
		if (sender != null)
			sender.sendMessage((isprefix ? prefix : "") + text);
	}

	protected static Player getPlayer(String name) {
		return Bukkit.getPlayer(name);
	}

	protected static String replaceColor(String str) {
		return str.replace("&1", "」1").replace("&2", "」2").replace("&3", "」3").replace("&4", "」4").replace("&5", "」5")
				.replace("&6", "」6").replace("&7", "」7").replace("&8", "」8").replace("&9", "」9").replace("&0", "」0")
				.replace("&a", "」a").replace("&b", "」b").replace("&c", "」c").replace("&d", "」d").replace("&e", "」e")
				.replace("&f", "」f").replace("&r", "」r").replace("&l", "」l").replace("&o", "」o");
	}

	protected static boolean isIntegerPositive(String string) {
		return string.matches("[0-9]+");
	}

	protected static List<String> makePage(List<String> list, int page, int size, boolean sort) {
		if (page <= 0 || page * size - (size - 1) > list.size())
			return null;
		if (sort)
			Collections.sort(list);
		List<String> contents = new ArrayList<>();
		for (int i = (page - 1) * size; i < page * size; i++) {
			contents.add(list.get(i));
			if (list.size() == (i + 1))
				break;
		}
		return contents;
	}
}