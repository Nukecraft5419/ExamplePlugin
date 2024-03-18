package dev.nukecraft5419.exampleplugin.listeners;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
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

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onJoinPlayer(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String displayName = player.getDisplayName();

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&eHello" + "&c" + displayName + "&6this is example message join"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"Version" + "&6" + ExamplePluginAPI.getIntVersion()[0] + "." + ExamplePluginAPI.getIntVersion()[1] + "&6" + ExamplePluginAPI.getServerVersion()));
    }
}
