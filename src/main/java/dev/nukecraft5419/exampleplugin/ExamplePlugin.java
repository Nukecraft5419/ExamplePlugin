package dev.nukecraft5419.exampleplugin;

import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.listeners.PlayerJoinListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

public class ExamplePlugin extends JavaPlugin {
    private void addListener(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        new ExamplePluginAPI(this);
        saveDefaultConfig();

        ExamplePluginAPI.getServer().sendMessage("&eExamplePlugin &asuccessfully loaded!");

        // Register events
        this.addListener(new PlayerJoinListener(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ExamplePluginAPI.getServer().sendMessage("&eExamplePlugin &asuccessfully disabled!");
    }
}
