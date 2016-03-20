package com.github.yuttyann.customchat;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.yuttyann.customchat.command.CCCommand;
import com.github.yuttyann.customchat.listener.ChatListener;

public class Main extends JavaPlugin implements Listener {

	private Logger logger = Logger.getLogger("Minecraft");
	private HashMap<String, CommandExecutor> commands;

	public void onEnable() {
		loadClass();
		loadCommand();
		saveDefaultConfig();
		PluginDescriptionFile yml = getDescription();
		this.logger.info("[" + yml.getName() + "] v" + yml.getVersion() + " が有効になりました");
	}

	public void onDisable() {
		PluginDescriptionFile yml = getDescription();
		this.logger.info("[" + yml.getName() + "] v" + yml.getVersion() + " が無効になりました");
	}

	private void loadClass() {
		new ChatListener(this);
	}

	private void loadCommand() {
		commands = new HashMap<String, CommandExecutor>();
		commands.put("cc", new CCCommand(this));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return commands.get(command.getName()).onCommand(sender, command, label, args);
	}
}
