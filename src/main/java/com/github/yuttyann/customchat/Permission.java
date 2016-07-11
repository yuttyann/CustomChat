package com.github.yuttyann.customchat;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Permission {
	CUSTOMCHAT_RELOAD("customchat.reload");

	private String node;

	private Permission(String node) {
		this.node = node;
	}

	public String getNode() {
		return node;
	}

	public static Boolean has(Permission permission, CommandSender sender) {
		return sender.hasPermission(permission.getNode());
	}

	public static Boolean has(Permission permission, Player player) {
		return player.hasPermission(permission.getNode());
	}
}
