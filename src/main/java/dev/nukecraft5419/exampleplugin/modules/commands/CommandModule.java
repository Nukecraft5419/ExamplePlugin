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
package dev.nukecraft5419.exampleplugin.modules.commands;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.commands.MainCommand;
import dev.nukecraft5419.exampleplugin.modules.PluginModule;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;

/**
 * Module responsible for registering and managing the plugin's main commands.
 * It isolates the command registration logic from the main plugin class.
 */
public class CommandModule implements PluginModule {

    private final ExamplePlugin plugin;

    public CommandModule(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {
        MainCommand mainCommand = new MainCommand();

        PluginCommand command = plugin.getCommand("exampleplugin");
        if (command != null) {
            command.setExecutor(mainCommand);
            command.setTabCompleter(mainCommand);
        } else {
            plugin.getLogger().warning("Command 'exampleplugin' not found in plugin.yml! Registration failed.");
        }
    }

    @Override
    public void onDisable() {
        // Commands are automatically unregistered by Bukkit on server shutdown/reload.
        // No manual cleanup is required here.
    }

    @Override
    public String getName() {
        return "CommandModule";
    }
}
