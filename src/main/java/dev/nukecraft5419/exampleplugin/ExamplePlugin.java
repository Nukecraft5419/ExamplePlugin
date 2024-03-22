package dev.nukecraft5419.exampleplugin;

import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.commands.MainCommand;
import dev.nukecraft5419.exampleplugin.listeners.PlayerJoinListener;
import dev.nukecraft5419.exampleplugin.utils.MessagesUtils;
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

        ExamplePluginAPI.getServer().sendMessage(MessagesUtils.getColorMessage("&eExamplePlugin &asuccessfully enabled!"));

        // Register events
        this.addListener(new PlayerJoinListener(this));

        // Register commands
        getCommand("exampleplugin").setExecutor(new MainCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ExamplePluginAPI.getServer().sendMessage(MessagesUtils.getColorMessage("&eExamplePlugin &cwas successfully disabled!"));
    }
}
