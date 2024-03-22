package dev.nukecraft5419.exampleplugin.utils;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.listeners.PlayerJoinListener;
import org.bukkit.event.Listener;

public class RegisterEventsUtils {
    private ExamplePlugin plugin;

    public RegisterEventsUtils(ExamplePlugin plugin) {
        this.plugin = plugin;
    }


    private void addListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
    public void registerEvents() {
        this.addListener(new PlayerJoinListener(plugin));
    }
}
