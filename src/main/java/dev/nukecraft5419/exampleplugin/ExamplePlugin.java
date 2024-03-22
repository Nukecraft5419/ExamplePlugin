package dev.nukecraft5419.exampleplugin;

import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.commands.MainCommand;
import dev.nukecraft5419.exampleplugin.config.MainConfigManager;
import dev.nukecraft5419.exampleplugin.utils.MessagesUtils;
import dev.nukecraft5419.exampleplugin.utils.RegisterCommandsUtils;
import dev.nukecraft5419.exampleplugin.utils.RegisterEventsUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {
    private MainConfigManager mainConfigManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        new ExamplePluginAPI(this);
        // Register commands
        RegisterCommandsUtils registerCommandsUtils = new RegisterCommandsUtils(this);
        // Register events
        RegisterEventsUtils registerEventsUtils = new RegisterEventsUtils(this);
        mainConfigManager = new MainConfigManager(this);
        ExamplePluginAPI.getServer().sendMessage(MessagesUtils.getColorMessage("%prefix% &asuccessfully enabled!").replace("%prefix%", this.getMainConfigManager().getPluginPrefix()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ExamplePluginAPI.getServer().sendMessage(MessagesUtils.getColorMessage("%prefix% &cwas successfully disabled!").replace("%prefix%", this.getMainConfigManager().getPluginPrefix()));
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }
}
