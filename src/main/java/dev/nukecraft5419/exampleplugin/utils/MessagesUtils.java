package dev.nukecraft5419.exampleplugin.utils;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import org.bukkit.ChatColor;

public class MessagesUtils {
    private ExamplePlugin plugin;
    private String no_permission;

    public MessagesUtils(ExamplePlugin plugin, String no_permission) {
        this.plugin = plugin;
    }

    public static String getColorMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
