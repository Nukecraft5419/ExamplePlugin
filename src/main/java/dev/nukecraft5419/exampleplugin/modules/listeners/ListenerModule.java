/*
 * MIT License
 *
 * Copyright (c) 2026 Nukecraft5419
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
package dev.nukecraft5419.exampleplugin.modules.listeners;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.config.ModuleConfigManager;
import dev.nukecraft5419.exampleplugin.listeners.PlayerJoinListener;
import dev.nukecraft5419.exampleplugin.modules.PluginModule;
import dev.nukecraft5419.nukelexicon.utils.SendUtils;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Module responsible for registering all the event listeners of the plugin.
 */
public class ListenerModule implements PluginModule {

    private final ExamplePlugin plugin;
    private final ModuleConfigManager moduleConfig = ExamplePluginAPI.getModuleManager().getModuleConfig();
    private final List<Listener> registeredListeners = new ArrayList<>();

    public ListenerModule(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {

        // Register the join event listener
        if (moduleConfig.isListenerEnabled("PlayerJoinListener")) {
            addListener(new PlayerJoinListener());
            SendUtils.log("<green>Loaded listener:</green> <yellow>PlayerJoinListener</yellow>");
        }
    }

    private void addListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        registeredListeners.add(listener);
    }

    @Override
    public void onDisable() {
        // Bukkit automatically unregisters events when the plugin is disabled.
        // No manual cleanup needed here.
        for (Listener listener : registeredListeners) {
            HandlerList.unregisterAll(listener);
        }

        SendUtils.log("<red>Unregistered " + registeredListeners.size() + " listeners.</red>");

        registeredListeners.clear();
    }

    @Override
    public String getName() {
        return "ListenerModule";
    }

}
