package com.github.yuttyann.customchat.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.yuttyann.customchat.Main;
import com.github.yuttyann.customchat.converter.KanaConverter;

public class ChatListener implements Listener {

	Main plugin;

	private String URL = "https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";
	private Pattern pattern = Pattern.compile("[^\u0020-\u007E]|\u00a7|u00a74u00a75u00a73u00a74v");

	public ChatListener(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		Matcher matcher = pattern.matcher(event.getMessage());
		Player player = event.getPlayer();
		String message = event.getMessage();
		String jp = message.replaceAll(URL, "");
		if (!matcher.find(0)) {
			if (plugin.getConfig().isSet("Players." + event.getPlayer().getName())) {
				String Group = plugin.getConfig().getString("Players." + player.getName() + ".Group");
				String Chat = plugin.getConfig().getString("ChatGroups." + Group);
				Chat = replace(Chat, message, jp, player, true);
				event.setFormat(Chat);
			} else {
				String Group = plugin.getConfig().getString("NormalPlayers");
				String Chat = plugin.getConfig().getString("ChatGroups." + Group);
				Chat = replace(Chat, message, jp, player, true);
				event.setFormat(Chat);
			}
		} else {
			if (plugin.getConfig().isSet("Players." + event.getPlayer().getName())) {
				String Group = plugin.getConfig().getString("Players." + player.getName() + ".Group");
				String Chat = plugin.getConfig().getString("ChatGroups." + Group);
				Chat = replace(Chat, message, jp, player, false);
				event.setFormat(Chat);
			} else {
				String Group = plugin.getConfig().getString("NormalPlayers");
				String Chat = plugin.getConfig().getString("ChatGroups." + Group);
				Chat = replace(Chat, message, jp, player, false);
				event.setFormat(Chat);
			}
		}
	}

	private String replace(String chat, String message, String jp, Player player, boolean japanize) {
		if(japanize) {
			chat = chat.replaceAll("&", "§");
			chat = chat.replaceAll("%player", player.getName());
			chat = chat.replaceAll("%chat", message);
			chat = chat.replaceAll("%jp", KanaConverter.conv(jp));
			return chat;
		} else {
			chat = chat.replaceAll("&", "§");
			chat = chat.replaceAll("%player", player.getName());
			chat = chat.replaceAll("%chat", message);
			chat = chat.replaceAll("%jp", "");
			return chat;
		}
	}
}
