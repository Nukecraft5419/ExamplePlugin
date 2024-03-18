package dev.nukecraft5419.exampleplugin.api;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;

public class ExamplePluginAPI {
    private static ExamplePlugin plugin;
    private static final Server server = Bukkit.getServer();

    public ExamplePluginAPI(ExamplePlugin plugin) {
        ExamplePluginAPI.plugin = plugin;
    }

    public static String getFolder() {
        return plugin.getDataFolder().getAbsolutePath();
    }

    public static String getVersion() {
        return  plugin.getDescription().getVersion();
    }

    public static String getName() {
        return plugin.getDescription().getAuthors().toString();
    }

    public static String getApiVersion(){
        return plugin.getDescription().getAPIVersion();
    }

    public static ConsoleCommandSender getServer() {
        Server server = Bukkit.getServer();
        return server.getConsoleSender();
    }

    public static String getServerVersion() {
        String pkg = server.getClass().getPackage().getName();
        return pkg.substring(pkg.lastIndexOf('.') + 1);
    }

    public static int[] getIntVersion() {
        int version = Integer.parseInt(server.getClass().getName().split("\\.")[3].split("_")[1]);
        int release = Integer.parseInt(server.getClass().getName().split("\\.")[3].split("R")[1]);
        return new int[]{ version, release };
    }
}
