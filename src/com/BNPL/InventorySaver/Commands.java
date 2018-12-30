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
			log("��Ŷ �������δ� ����� �Ұ����� ��ɾ� �Դϴ�.");
			return false;
		}
		Player player = (Player) sender;
		if (label.equals("�κ��丮")) {
			if (!player.isOp()) {
				sendMessage(player, "����� ������ �����ϴ�.");
				return false;
			}
			if (args.length == 0) {
				sendMessage(player, "��a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
				sendMessage(player, "", false);
				sendMessage(player, "/�κ��丮 ���� <�̸�> ��a: ���� �κ��丮�� �����մϴ�.");
				sendMessage(player, "/�κ��丮 �ε� <�̸�> ��a: �ش� �κ��丮�� �ε��մϴ�.");
				sendMessage(player, "/�κ��丮 ���� <�̸�> ��a: �ش� �κ��丮�� �����մϴ�.");
				sendMessage(player, "/�κ��丮 ��� <������> ��a: �κ��丮 ����� Ȯ���մϴ�.");
				sendMessage(player, "", false);
				sendMessage(player, "��a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
				return false;
			}
			if (args[0].equals("����")) {
				if (args.length != 2) {
					sendMessage(player, "/�κ��丮 ���� <�̸�> ��a: ���� �κ��丮�� �����մϴ�.");
					return false;
				}
				PlayerInventory inv = player.getInventory();
				if (inv.getContents().length == 0 && inv.getArmorContents().length == 0) {
					sendMessage(player, "�κ��丮�� �������� �����ϴ�.");
					return false;
				}
				new CustomInventory(args[1]).save(inv);
				sendMessage(player, "������ �Ϸ�Ǿ����ϴ�.");
				return false;
			}
			if (args[0].equals("�ε�")) {
				if (args.length != 2) {
					sendMessage(player, "/�κ��丮 �ε� <�̸�> ��a: �ش� �κ��丮�� �ε��մϴ�.");
					return false;
				}
				if (!CustomInventory.containsInventory(args[1])) {
					sendMessage(player, "�������� �ʴ� �κ��丮 �Դϴ�.");
					return false;
				}
				new CustomInventory(args[1]).load(player);
				// sendMessage(player, "�κ��丮�� �ҷ��Խ��ϴ�.");
				return false;
			}
			if (args[0].equals("����")) {
				if (args.length != 2) {
					sendMessage(player, "/�κ��丮 ���� <�̸�> ��a: �ش� �κ��丮�� �����մϴ�.");
					return false;
				}
				if (!CustomInventory.containsInventory(args[1])) {
					sendMessage(player, "�������� �ʴ� �κ��丮 �Դϴ�.");
					return false;
				}
				new CustomInventory(args[1]).delete();
				sendMessage(player, "������ �Ϸ�Ǿ����ϴ�.");
				return false;
			}
			if (args[0].equals("���")) {
				if (args.length != 2) {
					sendMessage(player, "/�κ��丮 ��� <������> ��a: �κ��丮 ����� Ȯ���մϴ�.");
					return false;
				}
				if (!isIntegerPositive(args[1])) {
					sendMessage(player, "�������� ������ �Է� �����մϴ�.");
					return false;
				}
				List<String> page = Util.makePage(CustomInventory.getInventoryList(), Integer.parseInt(args[1]), 8, true);
				if (page == null) {
					sendMessage(player, "�������� �ʴ� ������ �Դϴ�.");
					return false;
				}
				sendMessage(player, "��a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
				sendMessage(player, "��f", false);
				sendMessage(player,
						"[ " + args[1] + " / " + ((int) Math.ceil(CustomInventory.getInventoryList().size() / 8)) + " ������ ]");
				sendMessage(player, "��f", false);
				for (String inv : page)
					sendMessage(player, inv);
				sendMessage(player, "��f", false);
				sendMessage(player, "��a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
				return false;
			}
			sendMessage(player, "��a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
			sendMessage(player, "", false);
			sendMessage(player, "/�κ��丮 ���� <�̸�> ��a: ���� �κ��丮�� �����մϴ�.");
			sendMessage(player, "/�κ��丮 �ε� <�̸�> ��a: �ش� �κ��丮�� �ε��մϴ�.");
			sendMessage(player, "/�κ��丮 ���� <�̸�> ��a: �ش� �κ��丮�� �����մϴ�.");
			sendMessage(player, "/�κ��丮 ��� <������> ��a: �κ��丮 ����� Ȯ���մϴ�.");
			sendMessage(player, "", false);
			sendMessage(player, "��a-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-", false);
			return false;
		}
		return false;
	}

}
