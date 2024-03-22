package dev.nukecraft5419.exampleplugin;

import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.commands.MainCommand;
import dev.nukecraft5419.exampleplugin.utils.MessagesUtils;
import dev.nukecraft5419.exampleplugin.utils.RegisterCommandsUtils;
import dev.nukecraft5419.exampleplugin.utils.RegisterEventsUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        new ExamplePluginAPI(this);
        saveDefaultConfig();

        ExamplePluginAPI.getServer().sendMessage(MessagesUtils.getColorMessage("&eExamplePlugin &asuccessfully enabled!"));

        // Register events
        RegisterEventsUtils registerEventsUtils = new RegisterEventsUtils(this);

        // Register commands
        RegisterCommandsUtils registerCommandsUtils = new RegisterCommandsUtils(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ExamplePluginAPI.getServer().sendMessage(MessagesUtils.getColorMessage("&eExamplePlugin &cwas successfully disabled!"));
    }
}
