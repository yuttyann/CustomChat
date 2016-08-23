package com.github.yuttyann.customchat.japanize;

import org.bukkit.entity.Player;

import com.github.yuttyann.customchat.Main;
import com.github.yuttyann.customchat.file.Config;
import com.github.yuttyann.customchat.file.PlayerData;

public class JapanizeManager {

	private static final String URL = "https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";

	public static String addJapanize(Player player, String message) {
		PlayerData.load(Main.instance, player);
		if (!PlayerData.getBoolean("Japanize")) {
			return "";
		}
		if (message.getBytes().length == message.length() && !message.matches("[ \\uFF61-\\uFF9F]+")) {
			String japanizeformat = Config.getString("JapanizeFormat");
			String japanize = message.replaceAll(URL , "");
			String type = Config.getString("JapanizeType");
			if (type.toLowerCase().equals("kana")) {
				japanize = KanaConverter.conv(japanize);
			} else if (type.toLowerCase().equals("kanzi")) {
				japanize = KanaConverter.conv(japanize);
				japanize = IMEConverter.convByGoogleIME(japanize);
			}
			if (japanizeformat.contains("%japanize") && japanize.equals("")) {
				return "";
			}
			japanizeformat = japanizeformat.replace("&", "§");
			japanizeformat = japanizeformat.replace("%japanize", japanize);
			return japanizeformat;
		}
		return "";
	}
}
