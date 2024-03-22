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

    public static String getVersionPlugin() {
        return  plugin.getDescription().getVersion();
    }

    public static String getName() {
        return plugin.getDescription().getAuthors().get(0);
    }

    public static String getServerApiVersion(){
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
}
