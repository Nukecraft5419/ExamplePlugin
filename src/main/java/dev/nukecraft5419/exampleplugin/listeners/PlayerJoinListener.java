package dev.nukecraft5419.exampleplugin.listeners;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.utils.MessagesUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private ExamplePlugin plugin;

    public PlayerJoinListener(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String displayName = player.getDisplayName();
        String serverVersion = ExamplePluginAPI.getServerVersion();
        String versionPlugin = ExamplePluginAPI.getVersionPlugin();
        String serverApiVersion = ExamplePluginAPI.getServerApiVersion();

        if (plugin.getConfig().getBoolean("join.enabled")) {
            player.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getJoinMessage().replace("%displayname%", displayName)));
        }

        if (plugin.getConfig().getBoolean("server-info.enabled")) {
            player.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getServerInfoMessage().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix()).replace("%server_version%", serverVersion).replace("%version_plugin%", versionPlugin).replace("%server_api_version%", serverApiVersion)));
        }
    }
}