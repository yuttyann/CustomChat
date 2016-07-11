package com.github.yuttyann.customchat.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.yuttyann.customchat.Main;
import com.github.yuttyann.customchat.Permission;
import com.github.yuttyann.customchat.config.CustomChatConfig;
import com.github.yuttyann.customchat.config.CustomChatNGword;

public class CustomChatCommand implements CommandExecutor {

	Main plugin;

	public CustomChatCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!Permission.has(Permission.CUSTOMCHAT_RELOAD, sender)) {
			sender.sendMessage(ChatColor.RED + "パーミッションが無いため、実行できません。");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				CustomChatConfig.reloadConfig();
				CustomChatNGword.reloadConfig();
				sender.sendMessage(ChatColor.GREEN + "Configの再読み込みが完了しました。");
				return true;
			}
		}
		sender.sendMessage(ChatColor.LIGHT_PURPLE + "=== CustomChat Commands ===");
		sender.sendMessage(ChatColor.AQUA + "/customchat reload - Configの再読み込みをします。");
		return true;
	}
}
