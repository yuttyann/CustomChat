package com.github.yuttyann.customchat;

import org.bukkit.command.CommandSender;

public enum Permission {
	CUSTOMCHAT_COMMAND_RELOAD("customchat.command.reload"),
	CUSTOMCHAT_COMMAND_JAPANIZE("customchat.command.japanize");

	private String node;

	private Permission(String node) {
		this.node = node;
	}

	public String getNode() {
		return node;
	}

	public static String getPermission(Permission permission) {
		return permission.getNode();
	}

	public static Boolean has(Permission permission, CommandSender sender) {
		return sender.hasPermission(getPermission(permission));
	}

	public static Boolean has(String permission, CommandSender sender) {
		return sender.hasPermission(permission);
	}
}
