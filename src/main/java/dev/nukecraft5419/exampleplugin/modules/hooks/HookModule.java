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
package dev.nukecraft5419.exampleplugin.modules.hooks;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.config.ModuleConfigManager;
import dev.nukecraft5419.exampleplugin.hooks.PlaceholderHook;
import dev.nukecraft5419.exampleplugin.modules.PluginModule;
import dev.nukecraft5419.nukelexicon.utils.SendUtils;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
 * Module responsible for managing all external plugin integrations (Hooks).
 */
public class HookModule implements PluginModule {

    private final ExamplePlugin plugin;
    private final ModuleConfigManager moduleConfig = ExamplePluginAPI.getModuleManager().getModuleConfig();

    public HookModule(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {

        // --- [ 1. PlaceholderAPI Hook ] ---
        if (moduleConfig.isHookEnabled("PlaceholderAPI")) {
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                new PlaceholderHook().register();
                SendUtils.log("<green>Loaded hook:</green> <yellow>PlaceholderAPI</yellow>");
            } else {
                plugin.getLogger().warning("PlaceholderAPI not found! The plugin will still work, but some placeholders might not parse.");
            }
        }
    }

    @Override
    public void onDisable() {
        // PlaceholderAPI handles unregistering automatically when the plugin disables
    }

    @Override
    public String getName() {
        return "HookModule";
    }
}
