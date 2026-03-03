package dev.nukecraft5419.exampleplugin.modules.listeners;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.listeners.PlayerJoinListener;
import dev.nukecraft5419.exampleplugin.modules.PluginModule;
import org.jetbrains.annotations.NotNull;

/**
 * Module responsible for registering all the event listeners of the plugin.
 */
public class ListenerModule implements PluginModule {

    private final ExamplePlugin plugin;

    public ListenerModule(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {
        // Register the join event listener
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoinListener(plugin), plugin);
    }

    @Override
    public void onDisable() {
        // Bukkit automatically unregisters events when the plugin is disabled.
        // No manual cleanup needed here.
    }

    @Override
    public String getName() {
        return "ListenerModule";
    }

}
