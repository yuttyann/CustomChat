package com.github.yuttyann.customchat;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.yuttyann.customchat.command.CommandTemplate;
import com.github.yuttyann.customchat.command.CustomChatCommand;
import com.github.yuttyann.customchat.file.Config;
import com.github.yuttyann.customchat.file.NGword;
import com.github.yuttyann.customchat.listener.ChatListener;
import com.github.yuttyann.customchat.listener.JoinQuitListener;
import com.github.yuttyann.customchat.util.Utils;

public class Main extends JavaPlugin {

	public static Main instance;
	private Logger logger;
	private PluginDescriptionFile pluginyml;
	private HashMap<String, TabExecutor> commands;

	@Override
	public void onEnable() {
		instance = this;
		setUpFile();
		loadClass();
		loadCommand();
		logger = Logger.getLogger("Minecraft");
		pluginyml = getDescription();
		logger.info("[" + pluginyml.getName() + "] v" + pluginyml.getVersion() + " が有効になりました。");
	}

	@Override
	public void onDisable() {
		logger.info("[" + pluginyml.getName() + "] v" + pluginyml.getVersion() + " が無効になりました。");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return commands.get(command.getName()).onCommand(sender, command, label, args);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return commands.get(command.getName()).onTabComplete(sender, command, label, args);
	}

	private void setUpFile() {
		if (Utils.isLinux() || Utils.isMac()) {
			new Config(this, "utf-8");
			new NGword(this, "utf-8");
		} else if (Utils.isWindows()) {
			if(Utils.isUpperVersion("1.9")) {
				new Config(this, "utf-8");
				new NGword(this, "utf-8");
			} else {
				new Config(this, "s-jis");
				new NGword(this, "s-jis");
			}
		}
	}

	private void loadClass() {
		getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);
		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		getServer().getPluginManager().registerEvents(new Updater(this), this);
	}

	private void loadCommand() {
		setCommandTemplate();
		commands = new HashMap<String, TabExecutor>();
		commands.put("customchat", new CustomChatCommand(this));
	}

	private void setCommandTemplate() {
		new CommandTemplate(this);
		CommandTemplate.addCommand("/customchat reload - Configの再読み込みをします。");
		CommandTemplate.addCommand("/customchat japanize <on | off> - チャットの日本語化を設定します。");
	}
}
