package com.BNPL.InventorySaver;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class Commands extends Util implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			log("버킷 권한으로는 사용이 불가능한 명령어 입니다.");
			return false;
		}
		Player player = (Player) sender;
		if (label.equals("인벤토리")) {
			if (!player.isOp()) {
				sendMessage(player, "당신은 권한이 없습니다.");
				return false;
			}
			if (args.length == 0) {
				sendMessage(player, "§a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
				sendMessage(player, "", false);
				sendMessage(player, "/인벤토리 저장 <이름> §a: 현재 인벤토리를 저장합니다.");
				sendMessage(player, "/인벤토리 로드 <이름> §a: 해당 인벤토리를 로드합니다.");
				sendMessage(player, "/인벤토리 삭제 <이름> §a: 해당 인벤토리를 삭제합니다.");
				sendMessage(player, "/인벤토리 목록 <페이지> §a: 인벤토리 목록을 확인합니다.");
				sendMessage(player, "", false);
				sendMessage(player, "§a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
				return false;
			}
			if (args[0].equals("저장")) {
				if (args.length != 2) {
					sendMessage(player, "/인벤토리 저장 <이름> §a: 현재 인벤토리를 저장합니다.");
					return false;
				}
				PlayerInventory inv = player.getInventory();
				if (inv.getContents().length == 0 && inv.getArmorContents().length == 0) {
					sendMessage(player, "인벤토리에 아이템이 없습니다.");
					return false;
				}
				new CustomInventory(args[1]).save(inv);
				sendMessage(player, "저장이 완료되었습니다.");
				return false;
			}
			if (args[0].equals("로드")) {
				if (args.length != 2) {
					sendMessage(player, "/인벤토리 로드 <이름> §a: 해당 인벤토리를 로드합니다.");
					return false;
				}
				if (!CustomInventory.containsInventory(args[1])) {
					sendMessage(player, "존재하지 않는 인벤토리 입니다.");
					return false;
				}
				new CustomInventory(args[1]).load(player);
				// sendMessage(player, "인벤토리를 불러왔습니다.");
				return false;
			}
			if (args[0].equals("삭제")) {
				if (args.length != 2) {
					sendMessage(player, "/인벤토리 삭제 <이름> §a: 해당 인벤토리를 삭제합니다.");
					return false;
				}
				if (!CustomInventory.containsInventory(args[1])) {
					sendMessage(player, "존재하지 않는 인벤토리 입니다.");
					return false;
				}
				new CustomInventory(args[1]).delete();
				sendMessage(player, "삭제가 완료되었습니다.");
				return false;
			}
			if (args[0].equals("목록")) {
				if (args.length != 2) {
					sendMessage(player, "/인벤토리 목록 <페이지> §a: 인벤토리 목록을 확인합니다.");
					return false;
				}
				if (!isIntegerPositive(args[1])) {
					sendMessage(player, "페이지는 정수만 입력 가능합니다.");
					return false;
				}
				List<String> page = Util.makePage(CustomInventory.getInventoryList(), Integer.parseInt(args[1]), 8, true);
				if (page == null) {
					sendMessage(player, "존재하지 않는 페이지 입니다.");
					return false;
				}
				sendMessage(player, "§a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
				sendMessage(player, "§f", false);
				sendMessage(player,
						"[ " + args[1] + " / " + ((int) Math.ceil(CustomInventory.getInventoryList().size() / 8)) + " 페이지 ]");
				sendMessage(player, "§f", false);
				for (String inv : page)
					sendMessage(player, inv);
				sendMessage(player, "§f", false);
				sendMessage(player, "§a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
				return false;
			}
			sendMessage(player, "§a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
			sendMessage(player, "", false);
			sendMessage(player, "/인벤토리 저장 <이름> §a: 현재 인벤토리를 저장합니다.");
			sendMessage(player, "/인벤토리 로드 <이름> §a: 해당 인벤토리를 로드합니다.");
			sendMessage(player, "/인벤토리 삭제 <이름> §a: 해당 인벤토리를 삭제합니다.");
			sendMessage(player, "/인벤토리 목록 <페이지> §a: 인벤토리 목록을 확인합니다.");
			sendMessage(player, "", false);
			sendMessage(player, "§a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
			return false;
		}
		return false;
	}

}
