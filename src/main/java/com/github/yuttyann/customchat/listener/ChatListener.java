package com.github.yuttyann.customchat.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.github.yuttyann.customchat.config.CustomChatConfig;
import com.github.yuttyann.customchat.config.CustomChatNGword;
import com.github.yuttyann.customchat.converter.KanaConverter;

public class ChatListener implements Listener {

	Main plugin;

	private String URL = "https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";
	private Pattern pattern = Pattern.compile("[^\u0020-\u007E]|\u00a7|u00a74u00a75u00a73u00a74v");

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
		Matcher matcher = pattern.matcher(message);
		String jp = message.replaceAll(URL, "");
		if (!matcher.find(0)) {
			if (CustomChatConfig.isSet("Players." + event.getPlayer().getName())) {
				String Group = CustomChatConfig.getString("Players." + player.getName() + ".Group");
				String Chat = CustomChatConfig.getString("ChatGroups." + Group);
				Chat = replace(Chat, message, jp, player, true);
				event.setFormat(Chat);
			} else {
				String Group = CustomChatConfig.getString("NormalPlayers");
				String Chat = CustomChatConfig.getString("ChatGroups." + Group);
				Chat = replace(Chat, message, jp, player, true);
				event.setFormat(Chat);
			}
		} else {
			if (CustomChatConfig.isSet("Players." + event.getPlayer().getName())) {
				String Group = CustomChatConfig.getString("Players." + player.getName() + ".Group");
				String Chat = CustomChatConfig.getString("ChatGroups." + Group);
				Chat = replace(Chat, message, jp, player, false);
				event.setFormat(Chat);
			} else {
				String Group = CustomChatConfig.getString("NormalPlayers");
				String Chat = CustomChatConfig.getString("ChatGroups." + Group);
				Chat = replace(Chat, message, jp, player, false);
				event.setFormat(Chat);
			}
		}
	}

	private String replace(String chat, String message, String jp, Player player, boolean japanize) {
		chat = chat.replace("%prefix", getPrefix(player));
		chat = chat.replace("%suffix", getSuffix(player));
		chat = chat.replace("%time", getTime());
		chat = chat.replace("%player", player.getName());
		chat = chat.replace("%world", player.getWorld().getName());
		chat = chat.replace("%message", message);
		if(japanize) {
			chat = chat.replace("%japanize", KanaConverter.conv(jp));
		} else {
			chat = chat.replace("%japanize", "");
		}
		chat = chat.replace("&", "§");
		if (CustomChatConfig.getBoolean("ChatColorCode.Enable")) {
			message = message.replace(CustomChatConfig.getString("ChatColorCode.ColorCode"), "§");
		}
		return chat;
	}

	private boolean isNGword(Player player, String message) {
		if(!CustomChatConfig.getBoolean("NGword.Enable")) {
			return false;
		}
		for(String ngword : CustomChatNGword.getStringList("NGword")) {
			for(String exception : CustomChatNGword.getStringList("Exception")) {
				if(message.contains(exception)) {
					return false;
				}
			}
			if(message.contains(ngword)) {
				String type = CustomChatConfig.getString("NGword.MessageType");
				String ngmessage = CustomChatConfig.getString("NGword.NGMessage");
				ngmessage = ngmessage.replace("%prefix", getPrefix(player));
				ngmessage = ngmessage.replace("%suffix", getSuffix(player));
				ngmessage = ngmessage.replace("%time", getTime());
				ngmessage = ngmessage.replace("%player", player.getName());
				ngmessage = ngmessage.replace("%world", player.getWorld().getName());
				ngmessage = ngmessage.replace("%message", message);
				ngmessage = ngmessage.replace("&", "§");
				switch (type) {
				case "broadcast":
					Bukkit.broadcastMessage(ngmessage);
					break;
				case "send":
					player.sendMessage(ngmessage);
					break;
				default:
					break;
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
				if (prefix != null) {
					return prefix;
				}
			}
		} else if (pm.isPluginEnabled("PermissionsEx")) {
			PermissionUser user = PermissionsEx.getPermissionManager().getUser(player);
			if (user != null) {
				prefix = user.getPrefix();
				if (prefix != null) {
					return prefix;
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
				if (suffix != null) {
					return suffix;
				}
			}
		} else if (pm.isPluginEnabled("PermissionsEx")) {
			PermissionUser user = PermissionsEx.getPermissionManager().getUser(player);
			if (user != null) {
				suffix = user.getSuffix();
				if (suffix != null) {
					return suffix;
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
