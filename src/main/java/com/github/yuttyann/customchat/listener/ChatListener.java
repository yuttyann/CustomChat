package com.github.yuttyann.customchat.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.milkbowl.vault.chat.Chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.github.yuttyann.customchat.Main;
import com.github.yuttyann.customchat.file.Config;
import com.github.yuttyann.customchat.file.NGword;
import com.github.yuttyann.customchat.japanize.JapanizeManager;
import com.github.yuttyann.customchat.util.Utils;

public class ChatListener implements Listener {

	Main plugin;

	public ChatListener(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(isNGword(player, message)) {
			event.setCancelled(true);
			return;
		}
		String group;
		String chat;
		String uuid = Utils.getUniqueId(player.getName()).toString();
		if (message.getBytes().length == message.length() && !message.matches("[ \\uFF61-\\uFF9F]+")) {
			if (Config.contains("Players." + uuid)) {
				group = Config.getString("Players." + uuid + ".Group");
				chat = Config.getString("ChatGroups." + group);
				chat = replace(chat, message, player, true);
				event.setFormat(chat);
			} else {
				group = Config.getString("NormalPlayers");
				chat = Config.getString("ChatGroups." + group);
				chat = replace(chat, message, player, true);
				event.setFormat(chat);
			}
		} else {
			if (Config.contains("Players." + uuid)) {
				uuid = Utils.getUniqueId(player.getName()).toString();
				group = Config.getString("Players." + uuid + ".Group");
				chat = Config.getString("ChatGroups." + group);
				chat = replace(chat, message, player, false);
				event.setFormat(chat);
			} else {
				group = Config.getString("NormalPlayers");
				chat = Config.getString("ChatGroups." + group);
				chat = replace(chat, message, player, false);
				event.setFormat(chat);
			}
		}
	}

	private String replace(String chat, String message, Player player, boolean japanize) {
		chat = chat.replace("%prefix", getPrefix(player));
		chat = chat.replace("%suffix", getSuffix(player));
		chat = chat.replace("%time", getTime());
		chat = chat.replace("%player", player.getName());
		chat = chat.replace("%world", player.getWorld().getName());
		chat = chat.replace("%message", message);
		if(japanize) {
			chat = chat.replace("%addjapanize", JapanizeManager.addJapanize(player, message));
		} else {
			chat = chat.replace("%addjapanize", "");
		}
		chat = chat.replace("&", "§");
		if (Config.getBoolean("ChatColorCode.Enable")) {
			message = message.replace(Config.getString("ChatColorCode.ColorCode"), "§");
		}
		return chat;
	}

	private boolean isNGword(Player player, String message) {
		if(!Config.getBoolean("NGword.Enable")) {
			return false;
		}
		for(String ngword : NGword.getStringList("NGword")) {
			for(String exception : NGword.getStringList("Exception")) {
				if(message.contains(exception)) {
					return false;
				}
			}
			if(message.contains(ngword)) {
				String type = Config.getString("NGword.MessageType");
				String ngmessage = Config.getString("NGword.NGMessage");
				ngmessage = ngmessage.replace("%prefix", getPrefix(player));
				ngmessage = ngmessage.replace("%suffix", getSuffix(player));
				ngmessage = ngmessage.replace("%time", getTime());
				ngmessage = ngmessage.replace("%player", player.getName());
				ngmessage = ngmessage.replace("%world", player.getWorld().getName());
				ngmessage = ngmessage.replace("%message", message);
				ngmessage = ngmessage.replace("&", "§");
				if (type.equals("broadcast")) {
					Bukkit.broadcastMessage(ngmessage);
				} else if (type.equals("send")) {
					player.sendMessage(ngmessage);
				} else {
					player.sendMessage(ngmessage);
				}
				return true;
			}
		}
		return false;
	}

	private String getPrefix(Player player) {
		PluginManager pm = plugin.getServer().getPluginManager();
		String prefix = "";
		if (pm.isPluginEnabled("Vault")) {
			Chat chat = null;
			RegisteredServiceProvider<Chat> chatProvider = plugin.getServer().getServicesManager().getRegistration(Chat.class);
			if (chatProvider != null) {
				chat = chatProvider.getProvider();
			}
			if (chat != null) {
				prefix = chat.getPlayerPrefix(player);
				if (prefix == null) {
					prefix = "";
				}
			}
		} else if (pm.isPluginEnabled("PermissionsEx")) {
			PermissionUser user = PermissionsEx.getPermissionManager().getUser(player);
			if (user != null) {
				prefix = user.getPrefix();
				if (prefix == null) {
					prefix = "";
				}
			}
		}
		return prefix;
	}

	private String getSuffix(Player player) {
		PluginManager pm = plugin.getServer().getPluginManager();
		String suffix = "";
		if (pm.isPluginEnabled("Vault")) {
			Chat chat = null;
			RegisteredServiceProvider<Chat> chatProvider = plugin.getServer().getServicesManager().getRegistration(Chat.class);
			if (chatProvider != null) {
				chat = chatProvider.getProvider();
			}
			if (chat != null) {
				suffix = chat.getPlayerSuffix(player);
				if (suffix == null) {
					suffix = "";
				}
			}
		} else if (pm.isPluginEnabled("PermissionsEx")) {
			PermissionUser user = PermissionsEx.getPermissionManager().getUser(player);
			if (user != null) {
				suffix = user.getSuffix();
				if (suffix == null) {
					suffix = "";
				}
			}
		}
		return suffix;
	}

	private String getTime() {
		Date date = new Date();
		SimpleDateFormat simpledate = new SimpleDateFormat("HH:mm:ss");
		return simpledate.format(date);
	}
}
