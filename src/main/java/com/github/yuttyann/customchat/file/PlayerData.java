package com.github.yuttyann.customchat.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Color;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.github.yuttyann.customchat.Main;
import com.github.yuttyann.customchat.util.Utils;
import com.google.common.base.Charsets;

public class PlayerData {

	private static Main plugin;

	private static String filename;
	private static File playerdatafile;
	private static YamlConfiguration playerdata;

	public PlayerData(Main plugin, Player player, String encode) {
		PlayerData.plugin = plugin;
		filename = player.getUniqueId() + "_" + encode + ".yml";
		playerdatafile = new File(plugin.getDataFolder(), "PlayerDatas/" + filename);
		playerdata = YamlConfiguration.loadConfiguration(playerdatafile);
	}

	public static void load(Main plugin, Player player) {
		if (Utils.isLinux() || Utils.isMac()) {
			new PlayerData(plugin, player, "utf-8");
		} else if (Utils.isWindows()) {
			if(Utils.isUpperVersion("1.9")) {
				new PlayerData(plugin, player, "utf-8");
			} else {
				new PlayerData(plugin, player, "s-jis");
			}
		} else {
			new PlayerData(plugin, player, "utf-8");
		}
	}

	@SuppressWarnings("deprecation")
	public static void reload() {
		playerdata = YamlConfiguration.loadConfiguration(playerdatafile);
		InputStream defPlayerDataStream = plugin.getResource(filename);
		if (defPlayerDataStream != null) {
			YamlConfiguration defPlayerData;
			if(Utils.isUpperVersion("1.9")) {
				defPlayerData = YamlConfiguration.loadConfiguration(new InputStreamReader(defPlayerDataStream, Charsets.UTF_8));
			} else {
				defPlayerData = YamlConfiguration.loadConfiguration(defPlayerDataStream);
			}
			playerdata.setDefaults(defPlayerData);
		}
	}

	public static File getFile() {
		return playerdatafile;
	}

	public static YamlConfiguration getPlayerData() {
		return playerdata;
	}

	public static void save() {
		try {
			playerdata.save(playerdatafile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save(File file) {
		try {
			playerdata.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save(String file) {
		try {
			playerdata.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addDefault(String path, Object value) {
		playerdata.addDefault(path, value);
	}

	public static void addDefault(Configuration defaults) {
		playerdata.addDefaults(defaults);
	}

	public static void addDefault(Map<String, Object> defaults) {
		playerdata.addDefaults(defaults);
	}

	public static void set(String path, Object value) {
		playerdata.set(path, value);
	}

	public static void setDefaults(Configuration defaults) {
		playerdata.setDefaults(defaults);
	}

	public static ConfigurationSection createSection(String path) {
		return playerdata.createSection(path);
	}

	public static ConfigurationSection createSection(String path, Map<?, ?> map) {
		return playerdata.createSection(path, map);
	}

	public static ConfigurationSection getConfigurationSection(String path) {
		return playerdata.getConfigurationSection(path);
	}

	public static Configuration getDefaults() {
		return playerdata.getDefaults();
	}

	public static ConfigurationSection getDefaultSection() {
		return playerdata.getDefaultSection();
	}

	public static ConfigurationSection getParent() {
		return playerdata.getParent();
	}

	public static Configuration getRoot() {
		return playerdata.getRoot();
	}

	public static String getCurrentPath() {
		return playerdata.getCurrentPath();
	}

	public static String getName() {
		return playerdata.getName();
	}

	public static String getString(String path) {
		return playerdata.getString(path);
	}

	public static String getString(String path, String def) {
		return playerdata.getString(path, def);
	}

	public static String saveToString() {
		return playerdata.saveToString();
	}

	public static Object get(String path) {
		return playerdata.get(path);
	}

	public static Object get(String path, Object def) {
		return playerdata.get(path, def);
	}

	public static Color getColor(String path) {
		return playerdata.getColor(path);
	}

	public static Color getColor(String path, Color def) {
		return playerdata.getColor(path, def);
	}

	public static ItemStack getItemStack(String path) {
		return playerdata.getItemStack(path);
	}

	public static ItemStack getItemStack(String path, ItemStack def) {
		return playerdata.getItemStack(path, def);
	}

	public static Vector getVector(String path) {
		return playerdata.getVector(path);
	}

	public static Vector getVector(String path, Vector def) {
		return playerdata.getVector(path, def);
	}

	public static FileConfigurationOptions options(String path) {
		return playerdata.options();
	}

	public static boolean contains(String path) {
		return playerdata.contains(path);
	}

	public static boolean getBoolean(String path) {
		return playerdata.getBoolean(path);
	}

	public static boolean getBoolean(String path, boolean def) {
		return playerdata.getBoolean(path, def);
	}

	public static boolean isString(String path) {
		return playerdata.isString(path);
	}

	public static boolean isColor(String path) {
		return playerdata.isColor(path);
	}

	public static boolean isItemStack(String path) {
		return playerdata.isItemStack(path);
	}

	public static boolean isVector(String path) {
		return playerdata.isVector(path);
	}

	public static boolean isBoolean(String path) {
		return playerdata.isBoolean(path);
	}

	public static boolean isOfflinePlayer(String path) {
		return playerdata.isOfflinePlayer(path);
	}

	public static boolean isConfigurationSection(String path) {
		return playerdata.isConfigurationSection(path);
	}

	public static boolean isInt(String path) {
		return playerdata.isInt(path);
	}

	public static boolean isDouble(String path) {
		return playerdata.isDouble(path);
	}

	public static boolean isLong(String path) {
		return playerdata.isLong(path);
	}

	public static boolean isSet(String path) {
		return playerdata.isSet(path);
	}

	public static boolean isList(String path) {
		return playerdata.isList(path);
	}

	public static int getInt(String path) {
		return playerdata.getInt(path);
	}

	public static int getInt(String path, int def) {
		return playerdata.getInt(path, def);
	}

	public static double getDouble(String path) {
		return playerdata.getDouble(path);
	}

	public static double getDouble(String path, double def) {
		return playerdata.getDouble(path, def);
	}

	public static long getLong(String path) {
		return playerdata.getLong(path);
	}

	public static long getLong(String path, long def) {
		return playerdata.getLong(path, def);
	}

	public static Map<String, Object> getValues(boolean deep) {
		return playerdata.getValues(deep);
	}

	public static Set<String> getKeys(boolean deep) {
		return playerdata.getKeys(deep);
	}

	public static List<?> getList(String path) {
		return playerdata.getList(path);
	}

	public static List<?> getList(String path, List<?> def) {
		return playerdata.getList(path, def);
	}

	public static List<Map<?, ?>> getMapList(String path) {
		return playerdata.getMapList(path);
	}

	public static List<String> getStringList(String path) {
		return playerdata.getStringList(path);
	}

	public static List<Boolean> getBooleanList(String path) {
		return playerdata.getBooleanList(path);
	}

	public static List<Character> getCharacterList(String path) {
		return playerdata.getCharacterList(path);
	}

	public static List<Integer> getIntegerList(String path) {
		return playerdata.getIntegerList(path);
	}

	public static List<Double> getDoubleList(String path) {
		return playerdata.getDoubleList(path);
	}

	public static List<Float> getFloatList(String path) {
		return playerdata.getFloatList(path);
	}

	public static List<Long> getLongList(String path) {
		return playerdata.getLongList(path);
	}

	public static List<Short> getShortList(String path) {
		return playerdata.getShortList(path);
	}

	public static List<Byte> getByteList(String path) {
		return playerdata.getByteList(path);
	}
}