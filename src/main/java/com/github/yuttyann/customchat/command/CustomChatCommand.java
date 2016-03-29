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
			sender.sendMessage(ChatColor.RED + "権限がありません");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(ChatColor.GOLD + "Commnad:" + ChatColor.YELLOW + " /customchat reload");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				CustomChatConfig.reloadConfig();
				CustomChatNGword.reloadConfig();
				sender.sendMessage(ChatColor.GREEN + "Configのリロードが完了しました");
				return true;
			}
		}
		return false;
	}
}
