package com.github.yuttyann.customchat;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.yuttyann.customchat.command.CustomChatCommand;
import com.github.yuttyann.customchat.config.CustomChatConfig;
import com.github.yuttyann.customchat.config.CustomChatNGword;
import com.github.yuttyann.customchat.listener.ChatListener;

public class Main extends JavaPlugin implements Listener {

	private Logger logger = Logger.getLogger("Minecraft");
	private HashMap<String, CommandExecutor> commands;

	public void onEnable() {
		setUpConfig();
		loadClass();
		loadCommand();
		PluginDescriptionFile yml = getDescription();
		this.logger.info("[" + yml.getName() + "] v" + yml.getVersion() + " が有効になりました。");
	}

	public void onDisable() {
		PluginDescriptionFile yml = getDescription();
		this.logger.info("[" + yml.getName() + "] v" + yml.getVersion() + " が無効になりました。");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return commands.get(command.getName()).onCommand(sender, command, label, args);
	}

	private void setUpConfig() {
		if ((PlatformUtils.isLinux()) || (PlatformUtils.isMac())) {
			new CustomChatConfig(this, "utf-8");
			new CustomChatNGword(this, "utf-8");
		} else if (PlatformUtils.isWindows()) {
			if(Version.isVersion("1.9")) {
				new CustomChatConfig(this, "utf-8");
				new CustomChatNGword(this, "utf-8");
			} else {
				new CustomChatConfig(this, "s-jis");
				new CustomChatNGword(this, "s-jis");
			}
		}
	}

	private void loadClass() {
		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		getServer().getPluginManager().registerEvents(new Updater(this), this);
	}

	private void loadCommand() {
		commands = new HashMap<String, CommandExecutor>();
		commands.put("customchat", new CustomChatCommand(this));
	}
}
