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
package dev.nukecraft5419.exampleplugin.config;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class MainConfigManager {
    private ExamplePlugin plugin;
    private CustomConfig configFile;

    // config
    private String pluginPrefix;
    private String pluginHello;
    private String pluginAuthor;
    private String pluginVersion;
    private  String pluginReload;
    private List<String> pluginHelp;
    private String errorsConsole;
    private String errorsNoPermission;
    private String errorsNoArgsGet;
    private Boolean joinMessageEnabled;
    private String joinMessage;
    private Boolean serverInfoMessageEnabled;
    private String serverInfoMessage;



    public MainConfigManager(ExamplePlugin plugin) {
        this.plugin = plugin;
        configFile = new CustomConfig("config.yml", null, plugin);
        configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = configFile.getConfig();

        pluginPrefix = config.getString("plugin.prefix");
        pluginHello = config.getString("plugin.hello");
        pluginAuthor = config.getString("plugin.author");
        pluginVersion = config.getString("plugin.version");
        pluginReload = config.getString("plugin.reload");
        pluginHelp = config.getStringList("plugin.help");
        errorsConsole = config.getString("errors.console");
        errorsNoPermission = config.getString("errors.no-permission");
        errorsNoArgsGet = config.getString("errors.no-args-get");
        joinMessageEnabled = config.getBoolean("join.enabled");
        joinMessage = config.getString("join.message");
        serverInfoMessageEnabled = config.getBoolean("server-info.enabled");
        serverInfoMessage = config.getString("server-info.message");
    }

    public void reloadConfig() {
        configFile.reloadConfig();
        loadConfig();
    }

    public String getPluginPrefix() {
        return pluginPrefix;
    }

    public String getPluginHello() {
        return pluginHello;
    }

    public String getPluginAuthor() {
        return pluginAuthor;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    public String getPluginReload() {
        return pluginReload;
    }

    public List<String> getPluginHelp() {
        return pluginHelp;
    }

    public String getErrorsConsole() {
        return errorsConsole;
    }

    public String getErrorsNoPermission() {
        return errorsNoPermission;
    }

    public String getErrorsNoArgsGet() {
        return errorsNoArgsGet;
    }

    public Boolean getJoinMessageEnabled() {
        return joinMessageEnabled;
    }

    public String getJoinMessage() {
        return joinMessage;
    }

    public Boolean getServerInfoMessageEnabled() {
        return serverInfoMessageEnabled;
    }

    public String getServerInfoMessage() {
        return serverInfoMessage;
    }
}
