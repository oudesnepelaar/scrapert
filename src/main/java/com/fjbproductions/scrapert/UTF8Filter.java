package com.fjbproductions.scrapert;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class UTF8Filter {

    /**
     * We need a LinkedHashMap to guarantee conservation of the order of sequence.
     * These replacements need to happen from complex to simple, in order to prevent
     * multibyte combos being broken up and not completely replaced.
     */
    private final Map<String, String> replacements = new LinkedHashMap<>();

    public UTF8Filter() {

        replacements.put("â\u0080\u0098", "\""); // double quote left
        replacements.put("â\u0080\u0099", "\""); // double quote right

        replacements.put("Ã ", "\u00E0"); //	à
        replacements.put("Ã¡", "\u00E1"); //	á
        replacements.put("Ã¢", "\u00E2"); //	â
        replacements.put("Ã£", "\u00E3"); //	ã
        replacements.put("Ã¤", "\u00E4"); //	ä
        replacements.put("Ã¥", "\u00E5"); //	å
        replacements.put("Ã¦", "\u00E6"); //	æ
        replacements.put("Ã§", "\u00E7"); //	ç
        replacements.put("Ã¨", "\u00E8"); //	è
        replacements.put("Ã©", "\u00E9"); //	é
        replacements.put("Ãª", "\u00EA"); //	ê
        replacements.put("Ã«", "\u00eb"); //	ë
        replacements.put("Ã¬", "\u00EC"); //	ì
        replacements.put("Ã­", "\u00ED"); //	í
        replacements.put("Ã®", "\u00EE"); //	î
        replacements.put("Ã¯", "\u00EF"); //	ï
        replacements.put("Ã°", "\u00F0"); //	ð
        replacements.put("Ã±", "\u00F1"); //	ñ
        replacements.put("Ã²", "\u00F2"); //	ò
        replacements.put("Ã³", "\u00F3"); //	ó
        replacements.put("Ã´", "\u00F4"); //	ô
        replacements.put("Ãµ", "\u00F5"); //	õ
        replacements.put("Ã¶", "\u00F6"); //	ö
        replacements.put("Ã·", "\u00F7"); //	÷
        replacements.put("Ã¸", "\u00F8"); //	ø
        replacements.put("Ã¹", "\u00F9"); //	ù
        replacements.put("Ãº", "\u00FA"); //	ú
        replacements.put("Ã»", "\u00FB"); //	û
        replacements.put("Ã¼", "\u00FC"); //	ü
        replacements.put("Ã½", "\u00FD"); //	ý
        replacements.put("Ã¾", "\u00FE"); //	þ
        replacements.put("Ã¿", "\u00FF"); //	ÿ

        // if no usable replacement char can be found, we simply drop it from the string
        // beneath this line, only replcaing with empty strings

        replacements.put("\u0082", ""); // control character

        replacements.put("Ã", ""); // Ã
        replacements.put("Â", ""); // Â
    }

    /**
     *  Filters out any incompatible codepoints from the text,
     *  so they won't show up as grabled characters on the display.
     */
    public String filter(String input) {

        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            input = input.replaceAll(entry.getKey(), entry.getValue());
        }

        return input;
    }
}
