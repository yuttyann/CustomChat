package com.github.yuttyann.customchat.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.yuttyann.customchat.Main;
import com.github.yuttyann.customchat.file.Config;
import com.github.yuttyann.customchat.file.PlayerData;

public class JoinQuitListener implements Listener {

	Main plugin;

	public JoinQuitListener(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event) {
		PlayerData.load(plugin, event.getPlayer());
		if (!PlayerData.contains("Japanize")) {
			PlayerData.set("Japanize", Config.getBoolean("DefaultJapanize"));
			PlayerData.save();
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerQuit(PlayerQuitEvent event) {
		PlayerData.load(plugin, event.getPlayer());
		if (!PlayerData.contains("Japanize")) {
			PlayerData.set("Japanize", Config.getBoolean("DefaultJapanize"));
			PlayerData.save();
		}
	}
}
