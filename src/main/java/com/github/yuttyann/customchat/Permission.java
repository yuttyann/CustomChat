package com.github.yuttyann.customchat;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Permission {
	CC_RELOAD("cc.reload");

	private String node;

	private Permission(String node) {
		this.node = node;
	}

	public String getNode() {
		return node;
	}

	public static Boolean hasPermission(Permission permission, CommandSender sender) {
		return sender.hasPermission(permission.getNode());
	}

	public static Boolean hasPermission(Permission permission, Player player) {
		return player.hasPermission(permission.getNode());
	}
}
