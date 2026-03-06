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
package dev.nukecraft5419.exampleplugin.config;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * Manages the configuration and toggling of plugin modules.
 * Reads from the modules.yml file to dynamically determine which features
 * should be loaded into memory during startup or reload.
 */
public class ModuleConfigManager {

    private final CustomConfig configFile;
    private FileConfiguration config;

    /**
     * Initializes the manager and loads the configuration data.
     *
     * @param plugin The main class instance of the plugin.
     */
    public ModuleConfigManager(@NotNull ExamplePlugin plugin) {
        this.configFile = new CustomConfig(plugin, "modules.yml");
        loadConfig();
    }

    /**
     * Updates the config instance in memory.
     */
    public void loadConfig() {
        this.config = configFile.getConfig();
    }

    /**
     * Reloads the configuration from disk and updates the memory cache.
     */
    public void reload() {
        configFile.reloadConfig();
        loadConfig();
    }

    /**
     * Checks if a specific external hook is enabled in the modules.yml.
     *
     * @param hookName The name of the hook (e.g., "PlaceholderAPI", "Vault").
     * @return true if enabled or missing, false if explicitly disabled.
     */
    public boolean isHookEnabled(String hookName) {
        return this.config.getBoolean("hooks." + hookName, true);
    }

    /**
     * Checks if a specific subcommand is enabled in the modules.yml.
     *
     * @param commandName The name of the command (e.g., "hello").
     * @return true if enabled or missing, false if explicitly disabled.
     */
    public boolean isCommandEnabled(String commandName) {
        return this.config.getBoolean("commands." + commandName, true);
    }

    /**
     * Checks if a specific listener is enabled in the modules.yml.
     *
     * @param listenerName The name of the listener class (e.g., "PlayerJoinListener").
     * @return true if enabled or missing, false if explicitly disabled.
     */
    public boolean isListenerEnabled(String listenerName) {
        return this.config.getBoolean("listeners." + listenerName, true);
    }
}
