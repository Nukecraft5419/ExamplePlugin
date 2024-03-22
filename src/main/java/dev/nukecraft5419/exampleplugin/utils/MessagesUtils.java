package dev.nukecraft5419.exampleplugin.utils;

import org.bukkit.ChatColor;

public class MessagesUtils {

    public static String getColorMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
