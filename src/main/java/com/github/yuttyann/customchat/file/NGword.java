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
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.github.yuttyann.customchat.Main;
import com.github.yuttyann.customchat.util.Utils;
import com.google.common.base.Charsets;

public class NGword {

	private static Main plugin;

	private static String filename;
	private static File ngwordfile;
	private static YamlConfiguration ngword;

	public NGword(Main plugin, String encode) {
		NGword.plugin = plugin;
		filename = "ngword_" + encode + ".yml";
		ngwordfile = new File(plugin.getDataFolder(), filename);
		if (!ngwordfile.exists()) {
			plugin.saveResource(filename, false);
		}
		ngword = YamlConfiguration.loadConfiguration(ngwordfile);
	}

	@SuppressWarnings("deprecation")
	public static void reload() {
		ngword = YamlConfiguration.loadConfiguration(ngwordfile);
		InputStream defNGwordStream = plugin.getResource(filename);
		if (defNGwordStream != null) {
			YamlConfiguration defNGword;
			if(Utils.isUpperVersion("1.9")) {
				defNGword = YamlConfiguration.loadConfiguration(new InputStreamReader(defNGwordStream, Charsets.UTF_8));
			} else {
				defNGword = YamlConfiguration.loadConfiguration(defNGwordStream);
			}
			ngword.setDefaults(defNGword);
		}
	}

	public static File getFile() {
		return ngwordfile;
	}

	public static YamlConfiguration getNGword() {
		return ngword;
	}

	public static void save() {
		try {
			ngword.save(ngwordfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save(File file) {
		try {
			ngword.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save(String file) {
		try {
			ngword.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addDefault(String path, Object value) {
		ngword.addDefault(path, value);
	}

	public static void addDefault(Configuration defaults) {
		ngword.addDefaults(defaults);
	}

	public static void addDefault(Map<String, Object> defaults) {
		ngword.addDefaults(defaults);
	}

	public static void set(String path, Object value) {
		ngword.set(path, value);
	}

	public static void setDefaults(Configuration defaults) {
		ngword.setDefaults(defaults);
	}

	public static ConfigurationSection createSection(String path) {
		return ngword.createSection(path);
	}

	public static ConfigurationSection createSection(String path, Map<?, ?> map) {
		return ngword.createSection(path, map);
	}

	public static ConfigurationSection getConfigurationSection(String path) {
		return ngword.getConfigurationSection(path);
	}

	public static Configuration getDefaults() {
		return ngword.getDefaults();
	}

	public static ConfigurationSection getDefaultSection() {
		return ngword.getDefaultSection();
	}

	public static ConfigurationSection getParent() {
		return ngword.getParent();
	}

	public static Configuration getRoot() {
		return ngword.getRoot();
	}

	public static String getCurrentPath() {
		return ngword.getCurrentPath();
	}

	public static String getName() {
		return ngword.getName();
	}

	public static String getString(String path) {
		return ngword.getString(path);
	}

	public static String getString(String path, String def) {
		return ngword.getString(path, def);
	}

	public static String saveToString() {
		return ngword.saveToString();
	}

	public static Object get(String path) {
		return ngword.get(path);
	}

	public static Object get(String path, Object def) {
		return ngword.get(path, def);
	}

	public static Color getColor(String path) {
		return ngword.getColor(path);
	}

	public static Color getColor(String path, Color def) {
		return ngword.getColor(path, def);
	}

	public static ItemStack getItemStack(String path) {
		return ngword.getItemStack(path);
	}

	public static ItemStack getItemStack(String path, ItemStack def) {
		return ngword.getItemStack(path, def);
	}

	public static Vector getVector(String path) {
		return ngword.getVector(path);
	}

	public static Vector getVector(String path, Vector def) {
		return ngword.getVector(path, def);
	}

	public static FileConfigurationOptions options(String path) {
		return ngword.options();
	}

	public static boolean contains(String path) {
		return ngword.contains(path);
	}

	public static boolean getBoolean(String path) {
		return ngword.getBoolean(path);
	}

	public static boolean getBoolean(String path, boolean def) {
		return ngword.getBoolean(path, def);
	}

	public static boolean isString(String path) {
		return ngword.isString(path);
	}

	public static boolean isColor(String path) {
		return ngword.isColor(path);
	}

	public static boolean isItemStack(String path) {
		return ngword.isItemStack(path);
	}

	public static boolean isVector(String path) {
		return ngword.isVector(path);
	}

	public static boolean isBoolean(String path) {
		return ngword.isBoolean(path);
	}

	public static boolean isOfflinePlayer(String path) {
		return ngword.isOfflinePlayer(path);
	}

	public static boolean isConfigurationSection(String path) {
		return ngword.isConfigurationSection(path);
	}

	public static boolean isInt(String path) {
		return ngword.isInt(path);
	}

	public static boolean isDouble(String path) {
		return ngword.isDouble(path);
	}

	public static boolean isLong(String path) {
		return ngword.isLong(path);
	}

	public static boolean isSet(String path) {
		return ngword.isSet(path);
	}

	public static boolean isList(String path) {
		return ngword.isList(path);
	}

	public static int getInt(String path) {
		return ngword.getInt(path);
	}

	public static int getInt(String path, int def) {
		return ngword.getInt(path, def);
	}

	public static double getDouble(String path) {
		return ngword.getDouble(path);
	}

	public static double getDouble(String path, double def) {
		return ngword.getDouble(path, def);
	}

	public static long getLong(String path) {
		return ngword.getLong(path);
	}

	public static long getLong(String path, long def) {
		return ngword.getLong(path, def);
	}

	public static Map<String, Object> getValues(boolean deep) {
		return ngword.getValues(deep);
	}

	public static Set<String> getKeys(boolean deep) {
		return ngword.getKeys(deep);
	}

	public static List<?> getList(String path) {
		return ngword.getList(path);
	}

	public static List<?> getList(String path, List<?> def) {
		return ngword.getList(path, def);
	}

	public static List<Map<?, ?>> getMapList(String path) {
		return ngword.getMapList(path);
	}

	public static List<String> getStringList(String path) {
		return ngword.getStringList(path);
	}

	public static List<Boolean> getBooleanList(String path) {
		return ngword.getBooleanList(path);
	}

	public static List<Character> getCharacterList(String path) {
		return ngword.getCharacterList(path);
	}

	public static List<Integer> getIntegerList(String path) {
		return ngword.getIntegerList(path);
	}

	public static List<Double> getDoubleList(String path) {
		return ngword.getDoubleList(path);
	}

	public static List<Float> getFloatList(String path) {
		return ngword.getFloatList(path);
	}

	public static List<Long> getLongList(String path) {
		return ngword.getLongList(path);
	}

	public static List<Short> getShortList(String path) {
		return ngword.getShortList(path);
	}

	public static List<Byte> getByteList(String path) {
		return ngword.getByteList(path);
	}
}