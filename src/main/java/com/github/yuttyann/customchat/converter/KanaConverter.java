package com.github.yuttyann.customchat.converter;

import java.util.HashMap;

public class KanaConverter {

	private static final HashMap<String, String[]> kana = new HashMap<String, String[]>();

	static {
		kana.put("", new String[] { "あ", "い", "う", "え", "お" });
		kana.put("k", new String[] { "か", "き", "く", "け", "こ" });
		kana.put("s", new String[] { "さ", "し", "す", "せ", "そ" });
		kana.put("t", new String[] { "た", "ち", "つ", "て", "と" });
		kana.put("n", new String[] { "な", "に", "ぬ", "ね", "の" });
		kana.put("h", new String[] { "は", "ひ", "ふ", "へ", "ほ" });
		kana.put("m", new String[] { "ま", "み", "む", "め", "も" });
		kana.put("y", new String[] { "や", "い", "ゆ", "いぇ", "よ" });
		kana.put("r", new String[] { "ら", "り", "る", "れ", "ろ" });
		kana.put("w", new String[] { "わ", "うぃ", "う", "うぇ", "を" });
		kana.put("g", new String[] { "が", "ぎ", "ぐ", "げ", "ご" });
		kana.put("z", new String[] { "ざ", "じ", "ず", "ぜ", "ぞ" });
		kana.put("j", new String[] { "じゃ", "じ", "じゅ", "じぇ", "じょ" });
		kana.put("d", new String[] { "だ", "ぢ", "づ", "で", "ど" });
		kana.put("b", new String[] { "ば", "び", "ぶ", "べ", "ぼ" });
		kana.put("p", new String[] { "ぱ", "ぴ", "ぷ", "ぺ", "ぽ" });
		kana.put("gy", new String[] { "ぎゃ", "ぎぃ", "ぎゅ", "ぎぇ", "ぎょ" });
		kana.put("gw", new String[] { "ぐぁ", "ぐぃ", "ぐぅ", "ぐぇ", "ぐぉ" });
		kana.put("zy", new String[] { "じゃ", "じぃ", "じゅ", "じぇ", "じょ" });
		kana.put("jy", new String[] { "じゃ", "じぃ", "じゅ", "じぇ", "じょ" });
		kana.put("dy", new String[] { "ぢゃ", "ぢぃ", "ぢゅ", "ぢぇ", "ぢょ" });
		kana.put("dh", new String[] { "でゃ", "でぃ", "でゅ", "でぇ", "でょ" });
		kana.put("dw", new String[] { "どぁ", "どぃ", "どぅ", "どぇ", "どぉ" });
		kana.put("by", new String[] { "びゃ", "びぃ", "びゅ", "びぇ", "びょ" });
		kana.put("py", new String[] { "ぴゃ", "ぴぃ", "ぴゅ", "ぴぇ", "ぴょ" });
		kana.put("v", new String[] { "ヴぁ", "ヴぃ", "ヴ", "ヴぇ", "ヴぉ" });
		kana.put("vy", new String[] { "ヴゃ", "ヴぃ", "ヴゅ", "ヴぇ", "ヴょ" });
		kana.put("sh", new String[] { "しゃ", "し", "しゅ", "しぇ", "しょ" });
		kana.put("sy", new String[] { "しゃ", "し", "しゅ", "しぇ", "しょ" });
		kana.put("c", new String[] { "か", "し", "く", "せ", "こ" });
		kana.put("ch", new String[] { "ちゃ", "ち", "ちゅ", "ちぇ", "ちょ" });
		kana.put("cy", new String[] { "ちゃ", "ち", "ちゅ", "ちぇ", "ちょ" });
		kana.put("f", new String[] { "ふぁ", "ふぃ", "ふ", "ふぇ", "ふぉ" });
		kana.put("fy", new String[] { "ふゃ", "ふぃ", "ふゅ", "ふぇ", "ふょ" });
		kana.put("fw", new String[] { "ふぁ", "ふぃ", "ふ", "ふぇ", "ふぉ" });
		kana.put("q", new String[] { "くぁ", "くぃ", "く", "くぇ", "くぉ" });
		kana.put("ky", new String[] { "きゃ", "きぃ", "きゅ", "きぇ", "きょ" });
		kana.put("kw", new String[] { "くぁ", "くぃ", "く", "くぇ", "くぉ" });
		kana.put("ty", new String[] { "ちゃ", "ちぃ", "ちゅ", "ちぇ", "ちょ" });
		kana.put("ts", new String[] { "つぁ", "つぃ", "つ", "つぇ", "つぉ" });
		kana.put("th", new String[] { "てゃ", "てぃ", "てゅ", "てぇ", "てょ" });
		kana.put("tw", new String[] { "とぁ", "とぃ", "とぅ", "とぇ", "とぉ" });
		kana.put("ny", new String[] { "にゃ", "にぃ", "にゅ", "にぇ", "にょ" });
		kana.put("hy", new String[] { "ひゃ", "ひぃ", "ひゅ", "ひぇ", "ひょ" });
		kana.put("my", new String[] { "みゃ", "みぃ", "みゅ", "みぇ", "みょ" });
		kana.put("ry", new String[] { "りゃ", "りぃ", "りゅ", "りぇ", "りょ" });
		kana.put("x", new String[] { "ぁ", "ぃ", "ぅ", "ぇ", "ぉ" });
		kana.put("ly", new String[] { "ゃ", "ぃ", "ゅ", "ぇ", "ょ" });
		kana.put("lt", new String[] { "た", "ち", "っ", "て", "と" });
		kana.put("lk", new String[] { "ヵ", "き", "く", "ヶ", "こ" });
		kana.put("xy", new String[] { "ゃ", "ぃ", "ゅ", "ぇ", "ょ" });
		kana.put("xt", new String[] { "た", "ち", "っ", "て", "と" });
		kana.put("xk", new String[] { "ヵ", "き", "く", "ヶ", "こ" });
		kana.put("wy", new String[] { "わ", "ゐ", "う", "ゑ", "を" });
		kana.put("wh", new String[] { "うぁ", "うぃ", "う", "うぇ", "うぉ" });
	};

	private static String getKanaFromTable(String s, int n) {
		if (kana.containsKey(s)) {
			return kana.get(s)[n];
		}
		return s + kana.get("")[n];
	}

	public static String conv(String org) {
		String last = "";
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < org.length(); i++) {
			String tmp = org.substring(i, i + 1);
			if (tmp.equals("a")) {
				line.append(getKanaFromTable(last, 0));
				last = "";
			} else if (tmp.equals("i")) {
				line.append(getKanaFromTable(last, 1));
				last = "";
			} else if (tmp.equals("u")) {
				line.append(getKanaFromTable(last, 2));
				last = "";
			} else if (tmp.equals("e")) {
				line.append(getKanaFromTable(last, 3));
				last = "";
			} else if (tmp.equals("o")) {
				line.append(getKanaFromTable(last, 4));
				last = "";
			} else {
				if (last.equals("n") && !(tmp.equals("y"))) {
					line.append("ん");
					last = "";
					if (tmp.equals("n")) {
						continue;
					}
				}
				if (Character.isLetter(tmp.charAt(0))) {
					if (Character.isUpperCase(tmp.charAt(0))) {
						line.append(last + tmp);
						last = "";
					} else if (last.equals(tmp)) {
						line.append("っ");
						last = tmp;
					} else {
						last = last + tmp;
					}
				} else {
					if (tmp.equals("-")) {
						line.append(last + "ー");
						last = "";
					} else if (tmp.equals("~")) {
						line.append(last + "～");
						last = "";
					} else if (tmp.equals(".")) {
						line.append(last + "・");
						last = "";
					} else if (tmp.equals(",")) {
						line.append(last + "、");
						last = "";
					} else if (tmp.equals("?")) {
						line.append(last + "？");
						last = "";
					} else if (tmp.equals("!")) {
						line.append(last + "！");
						last = "";
					} else if (tmp.equals("[")) {
						line.append(last + "「");
						last = "";
					} else if (tmp.equals("]")) {
						line.append(last + "」");
						last = "";
					} else {
						line.append(last + tmp);
						last = "";
					}
				}
			}
		}
		line.append(last);
		return line.toString();
	}
}