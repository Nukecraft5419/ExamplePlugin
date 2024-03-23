/*
 * MIT License
 *
 * Copyright (c) 2024 Nukecraft5419
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.nukecraft5419.exampleplugin.listeners;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.utils.MessagesUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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