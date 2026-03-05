package dev.nukecraft5419.exampleplugin.modules.listeners;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.config.ModuleConfigManager;
import dev.nukecraft5419.exampleplugin.listeners.PlayerJoinListener;
import dev.nukecraft5419.exampleplugin.modules.PluginModule;
import dev.nukecraft5419.exampleplugin.utils.SendUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Module responsible for registering all the event listeners of the plugin.
 */
public class ListenerModule implements PluginModule {

    private final ExamplePlugin plugin;
    private final ModuleConfigManager moduleConfig = ExamplePluginAPI.getModuleManager().getModuleConfig();

    public ListenerModule(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {

        // Register the join event listener
        if (moduleConfig.isListenerEnabled("PlayerJoinListener")) {
            plugin.getServer().getPluginManager().registerEvents(new PlayerJoinListener(plugin), plugin);
            SendUtils.log("<green>Loaded listener:</green> <yellow>PlayerJoinListener</yellow>");
        }
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
