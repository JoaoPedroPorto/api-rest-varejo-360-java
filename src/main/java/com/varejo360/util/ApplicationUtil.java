package com.varejo360.util;

public class ApplicationUtil {

	public static String removeCaracterSpecial(String text) {
		text = text.trim();
		if (text != null && !text.trim().isEmpty()) {
			text = text.replaceAll("[ãâàáä]",	"a");
			text = text.replaceAll("[êèéë&]",	"e");
			text = text.replaceAll("[îìíï]",	"i");
			text = text.replaceAll("[õôòóö]",	"o");
			text = text.replaceAll("[ûúùü]",	"u");
			text = text.replaceAll("[ÃÂÀÁÄ]",	"A");
			text = text.replaceAll("[ÊÈÉË]",	"E");
			text = text.replaceAll("[ÎÌÍÏ]",	"I");
			text = text.replaceAll("[ÕÔÒÓÖ]",	"O");
			text = text.replaceAll("[ÛÙÚÜ]",	"U");
			text = text.replace('ç',	'c');
			text = text.replace('Ç',	'C');
			text = text.replace('ñ',	'n');
			text = text.replace('Ñ',	'N');
		}
		return text;
	}
	
}