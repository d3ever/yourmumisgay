/*******************************************************
 * Copyright (C) 2017-2018 d3ever <d3ever@rewforce.cf>
 *
 * This file is part of sexy.
 *
 * sexy can not be copied and/or distributed without the express
 * permission of d3ever
 *
 * Date: 8/27/2018 - 19:57 Monday
 *
 *******************************************************/
package sexy.criss.simple.prison.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Scroller {
    private static final char COLOUR_CHAR = '§';
    private int position;
    private List<String> list;
    private ChatColor colour = ChatColor.RESET;

    public Scroller(String message, int width, int spaceBetween, char colourChar) {
        list = new ArrayList<>();

        if (message.length() < width) {
            StringBuilder sb = new StringBuilder(message);
            while (sb.length() < width)
                sb.append(" ");
            message = sb.toString();
        }

        width -= 2;

        if (width < 1) width = 1;
        if (spaceBetween < 0) spaceBetween = 0;

        if (colourChar != '§')
            message = Utils.f(message);


        for (int i = 0; i < message.length() - width; i++)
            list.add(message.substring(i, i + width));

        StringBuilder space = new StringBuilder();
        for (int i = 0; i < spaceBetween; ++i) {
            list.add(message.substring(message.length() - width + (i > width ? width : i)) + space);
            if (space.length() < width) space.append(" ");
        }

        for (int i = 0; i < width - spaceBetween; ++i)
            list.add(message.substring(message.length() - width + spaceBetween + i) + space + message.substring(0, i));

        for (int i = 0; i < spaceBetween; i++) {
            if (i > space.length()) break;
            list.add(space.substring(0, space.length() - i) + message.substring(0, width - (spaceBetween > width ? width : spaceBetween) + i));
        }
    }

    public String next() {
        StringBuilder sb = getNext();
        if (sb.charAt(sb.length() - 1) == COLOUR_CHAR) sb.setCharAt(sb.length() - 1, ' ');

        if (sb.charAt(0) == COLOUR_CHAR) {
            ChatColor c = ChatColor.getByChar(sb.charAt(1));
            if (c != null) {
                colour = c;
                sb = getNext();
                if (sb.charAt(0) != ' ') sb.setCharAt(0, ' ');
            }
        }

        return colour + sb.toString();
    }

    private StringBuilder getNext() {
        return new StringBuilder(list.get(position++ % list.size()));
    }

}

