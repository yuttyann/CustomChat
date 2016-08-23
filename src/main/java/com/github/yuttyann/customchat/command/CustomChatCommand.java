package com.github.yuttyann.customchat.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import com.github.yuttyann.customchat.Main;
import com.github.yuttyann.customchat.Permission;
import com.github.yuttyann.customchat.file.Config;
import com.github.yuttyann.customchat.file.NGword;
import com.github.yuttyann.customchat.file.PlayerData;

public class CustomChatCommand implements TabExecutor {

	Main plugin;

	public CustomChatCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				if (!Permission.has(Permission.CUSTOMCHAT_COMMAND_RELOAD, sender)) {
					sender.sendMessage(ChatColor.RED + "パーミッションが無いため、実行できません。");
					return true;
				}
				Config.reload();
				NGword.reload();
				sender.sendMessage(ChatColor.GREEN + "Configの再読み込みが完了しました。");
				return true;
			}
			if (args[0].equalsIgnoreCase("japanize")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "コマンドはゲーム内から実行してください。");
					return true;
				}
				if (!Permission.has(Permission.CUSTOMCHAT_COMMAND_RELOAD, sender)) {
					sender.sendMessage(ChatColor.RED + "パーミッションが無いため、実行できません。");
					return true;
				}
				Player player = (Player) sender;
				PlayerData.load(plugin, player);
				String setting;
				if (PlayerData.getBoolean("Japanize")) {
					setting = "ON";
				} else {
					setting = "OFF";
				}
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "現在の設定: " + setting);
				return true;
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("japanize")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "コマンドはゲーム内から実行してください。");
					return true;
				}
				if (!Permission.has(Permission.CUSTOMCHAT_COMMAND_RELOAD, sender)) {
					sender.sendMessage(ChatColor.RED + "パーミッションが無いため、実行できません。");
					return true;
				}
				boolean setting = false;
				if (args[1].equalsIgnoreCase("on")) {
					setting = true;
				}
				Player player = (Player) sender;
				PlayerData.load(plugin, player);
				if (setting) {
					player.sendMessage(ChatColor.AQUA + "チャットの日本語化を有効にしました。");
					PlayerData.set("Japanize", setting);
				} else {
					player.sendMessage(ChatColor.AQUA + "チャットの日本語化を無効にしました。");
					PlayerData.set("Japanize", setting);
				}
				PlayerData.save();
				return true;
			}
		}
		CommandTemplate.sendCommandTemplate(sender);
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1) {
			String prefix = args[0].toLowerCase();
			ArrayList<String> commands = new ArrayList<String>();
			for (String c : new String[]{"reload", "japanize"}) {
				if (c.startsWith(prefix)) {
					commands.add(c);
				}
			}
			return commands;
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("japanize")) {
				String prefix = args[1].toLowerCase();
				ArrayList<String> commands = new ArrayList<String>();
				for (String c : new String[]{"on", "off"}) {
					if (c.startsWith(prefix)) {
						commands.add(c);
					}
				}
				return commands;
			}
		}
		return null;
	}
}
