package com.fjbproductions.scrapert;

import java.nio.charset.StandardCharsets;

/**
 *
 */
public class UTF8Filter {

    /**
     *  Filters out any incompatible codepoints from the text,
     *  so they won't show up as grabled characters on the display.
     */
    public String filter(String input) {

        return input; // simple bypass mode

//        byte[] bytes = input.getBytes(StandardCharsets.ISO_8859_1);
//        String output = new String(bytes, StandardCharsets.ISO_8859_1);
//
//        return output;
    }
}
