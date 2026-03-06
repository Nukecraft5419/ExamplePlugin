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
 * Manages the main configuration file of the plugin.
 * Handles loading, reloading, and provides access to settings via getters.
 */
public class MainConfigManager {

    private final CustomConfig configFile;

    // --- [ Feature Settings ] ---
    private String fallbackLanguage;
    private Boolean joinMessageEnabled;
    private Boolean serverInfoMessageEnabled;

    /**
     * Initializes the manager and loads the configuration data.
     *
     * @param plugin The main class instance of the plugin.
     */
    public MainConfigManager(@NotNull ExamplePlugin plugin) {
        configFile = new CustomConfig(plugin, "config.yml");
        loadConfig();
    }

    /**
     * Loads raw values from the FileConfiguration into memory.
     * Note: Formatting and placeholders are handled at the time of sending.
     */
    public void loadConfig() {
        FileConfiguration config = configFile.getConfig();

        // Loading Language Settings
        this.fallbackLanguage = config.getString("fallback-language", "en_US");

        // Loading Join Settings
        this.joinMessageEnabled = config.getBoolean("join.enabled");

        // Loading Server Info Settings
        this.serverInfoMessageEnabled = config.getBoolean("server-info.enabled");
    }

    /**
     * Synchronizes the memory with the disk by reloading the file.
     */
    public void reloadConfig() {
        this.configFile.reloadConfig();
        this.loadConfig();
    }

    // --- [ Getters ] ---

    /**
     * Gets the fallback language used when a player's locale is not supported.
     * * @return The fallback language code (e.g., "en_us").
     */
    public String getFallbackLanguage() {
        return this.fallbackLanguage;
    }

    /**
     * Checks if the join message feature is enabled in the config.
     * @return true if the join message feature is active, false otherwise.
     */
    public Boolean getJoinMessageEnabled() {
        return this.joinMessageEnabled;
    }

    /**
     * Checks if the server info feature is enabled in the config.
     * @return true if the server info feature is active, false otherwise.
     */
    public Boolean getServerInfoMessageEnabled() {
        return this.serverInfoMessageEnabled;
    }
}
