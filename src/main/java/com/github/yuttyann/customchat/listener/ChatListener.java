package com.github.yuttyann.customchat.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
		if(japanize) {
			chat = chat.replace("%player", player.getName());
			chat = chat.replace("%chat", message);
			chat = chat.replace("%jp", KanaConverter.conv(jp));
			chat = chat.replace("&", "§");
			return chat;
		} else {
			chat = chat.replace("%player", player.getName());
			chat = chat.replace("%chat", message);
			chat = chat.replace("%jp", "");
			chat = chat.replace("&", "§");
			return chat;
		}
	}

	private static boolean isNGword(Player player, String message) {
		if(!CustomChatConfig.getBoolean("NGword.Enable")) {
			return false;
		}
		for(String exception : CustomChatNGword.getStringList("Exception")) {
			for(String ngword : CustomChatNGword.getStringList("NGword")) {
				if(message.contains(ngword) && !message.contains(exception)) {
					String type = CustomChatConfig.getString("NGword.MessageType");
					String ngmessage = CustomChatConfig.getString("NGword.NGMessage");
					ngmessage = ngmessage.replace("%player", player.getName());
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
		}
		return false;
	}
}
