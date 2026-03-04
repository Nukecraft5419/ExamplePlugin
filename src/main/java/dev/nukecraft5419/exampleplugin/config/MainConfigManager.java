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

import java.util.List;

/**
 * Manages the main configuration file of the plugin.
 * Handles loading, reloading, and provides access to settings via getters.
 */
public class MainConfigManager {

    private final ExamplePlugin plugin;
    private final CustomConfig configFile;

    // --- [ Plugin Information ] ---
    private String pluginPrefix;
    private String pluginHello;
    private String pluginAuthor;
    private String pluginVersion;
    private String pluginReload;
    private List<String> pluginHelp;

    // --- [ Error Messages ] ---
    private String errorsConsole;
    private String errorsNoPermission;
    private String errorsNoArgsGet;

    // --- [ Feature Settings ] ---
    private Boolean joinMessageEnabled;
    private String joinMessage;
    private Boolean serverInfoMessageEnabled;
    private String serverInfoMessage;

    /**
     * Initializes the manager and loads the configuration data.
     *
     * @param plugin The main class instance of the plugin.
     */
    public MainConfigManager(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;

        configFile = new CustomConfig(plugin, "config.yml");
        loadConfig();
    }

    /**
     * Loads raw values from the FileConfiguration into memory.
     * Note: Formatting and placeholders are handled at the time of sending.
     */
    public void loadConfig() {
        FileConfiguration config = configFile.getConfig();

        // Loading Plugin Section
        this.pluginPrefix = config.getString("plugin.prefix");
        this.pluginHello = config.getString("plugin.hello");
        this.pluginAuthor = config.getString("plugin.author");
        this.pluginVersion = config.getString("plugin.version");
        this.pluginReload = config.getString("plugin.reload");
        this.pluginHelp = config.getStringList("plugin.help");

        // Loading Errors Section
        this.errorsConsole = config.getString("errors.console");
        this.errorsNoPermission = config.getString("errors.no-permission");
        this.errorsNoArgsGet = config.getString("errors.no-args-get");

        // Loading Join Settings
        this.joinMessageEnabled = config.getBoolean("join.enabled");
        this.joinMessage = config.getString("join.message");

        // Loading Server Info Settings
        this.serverInfoMessageEnabled = config.getBoolean("server-info.enabled");
        this.serverInfoMessage = config.getString("server-info.message");
    }

    /**
     * Synchronizes the memory with the disk by reloading the file.
     */
    public void reloadConfig() {
        this.configFile.reloadConfig();
        this.loadConfig();
    }

    // --- [ Getters ] ---

    /** @return The prefix used in most plugin messages. */
    public String getPluginPrefix() {
        return this.pluginPrefix;
    }

    /** @return The greeting message. */
    public String getPluginHello() {
        return this.pluginHello;
    }

    /** @return The author's name as defined in config. */
    public String getPluginAuthor() {
        return this.pluginAuthor;
    }

    /** @return The internal version string. */
    public String getPluginVersion() {
        return this.pluginVersion;
    }

    /** @return The message sent upon configuration reload. */
    public String getPluginReload() {
        return this.pluginReload;
    }

    /** @return The list of strings for the help command. */
    public List<String> getPluginHelp() {
        return this.pluginHelp;
    }

    /** @return Message for console-only command errors. */
    public String getErrorsConsole() {
        return this.errorsConsole;
    }

    /** @return Message for permission denial. */
    public String getErrorsNoPermission() {
        return this.errorsNoPermission;
    }

    /** @return Message for incorrect command usage. */
    public String getErrorsNoArgsGet() {
        return this.errorsNoArgsGet;
    }

    /** @return Whether the join message feature is active. */
    public Boolean getJoinMessageEnabled() {
        return this.joinMessageEnabled;
    }

    /** @return The message sent when a player joins. */
    public String getJoinMessage() {
        return this.joinMessage;
    }

    /** @return Whether the server info feature is active. */
    public Boolean getServerInfoMessageEnabled() {
        return this.serverInfoMessageEnabled;
    }

    /** @return The informational message about the server. */
    public String getServerInfoMessage() {
        return this.serverInfoMessage;
    }
}
