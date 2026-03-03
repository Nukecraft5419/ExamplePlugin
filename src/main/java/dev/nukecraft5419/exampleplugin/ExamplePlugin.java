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
package dev.nukecraft5419.exampleplugin;

import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.hooks.PlaceholderHook;
import dev.nukecraft5419.exampleplugin.modules.ModuleManager;
import dev.nukecraft5419.exampleplugin.modules.commands.CommandModule;
import dev.nukecraft5419.exampleplugin.modules.listeners.ListenerModule;
import dev.nukecraft5419.exampleplugin.utils.SendUtils;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    private static final int BSTATS_ID = 29819;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {

        // You can find the plugin id of your plugins on
        // the page https://bstats.org/what-is-my-plugin-id
        new Metrics(this, BSTATS_ID);

        // Register the plugin API
        ExamplePluginAPI.register(this);

        // Check for PlaceholderAPI dependency
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {

            // Register PlaceholderAPI hook
            new PlaceholderHook(this).register();

            // Initialize the module manager
            this.moduleManager = new ModuleManager(this);
            this.moduleManager.registerModule(new ListenerModule(this));

            // Register all plugin modules
            this.moduleManager.registerModule(new CommandModule(this));

            // Load and enable all registered modules
            this.moduleManager.loadModules();

            SendUtils.log("<prefix> <green>successfully enabled!</green>");

        } else {
            this.getLogger().severe("Could not find PlaceholderAPI! This plugin is required.");
            this.getLogger().severe("Disabling ExamplePlugin...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {

        // Unload and disable all modules safely to prevent memory leaks
        if (this.moduleManager != null) {
            this.moduleManager.unloadModules();
        }

        SendUtils.log("<prefix> <red>was successfully disabled!</red>");

        // Unregister the plugin API
        ExamplePluginAPI.unregister();
    }
}
