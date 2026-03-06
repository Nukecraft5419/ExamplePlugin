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
package dev.nukecraft5419.exampleplugin.modules;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.config.ModuleConfigManager;
import dev.nukecraft5419.exampleplugin.modules.commands.CommandModule;
import dev.nukecraft5419.exampleplugin.modules.hooks.HookModule;
import dev.nukecraft5419.exampleplugin.modules.listeners.ListenerModule;
import dev.nukecraft5419.exampleplugin.utils.SendUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Core manager responsible for the lifecycle of all plugin modules.
 * It handles the registration, enabling, and disabling of features safely,
 * preventing memory leaks and ensuring strict dependency management based on modules.yml.
 */
public class ModuleManager {

    private final ExamplePlugin plugin;
    private final ModuleConfigManager moduleConfig;
    private final List<PluginModule> modules = new ArrayList<>();

    public ModuleManager(ExamplePlugin plugin) {
        this.plugin = plugin;

        this.moduleConfig = new ModuleConfigManager(plugin);
    }

    /**
     * Registers and loads all enabled modules based on modules.yml.
     */
    public void loadModules() {

        registerModule(new CommandModule(plugin));
        registerModule(new ListenerModule(plugin));
        registerModule(new HookModule(plugin));

        for (PluginModule module : modules) {
            try {
                module.onEnable();
                SendUtils.log("<green>Loaded module: <yellow>" + module.getName() + "</yellow></green>");
            } catch (Exception e) {
                SendUtils.log("<dark_red>Failed to load module: <red>" + module.getName() + "</red></dark_red>");
                plugin.getLogger().log(Level.SEVERE, "Critical error while enabling module: " + module.getName(), e);
            }
        }
    }

    /**
     * Adds a new module to the manager's list.
     * Note: This does not enable the module immediately.
     *
     * @param module The PluginModule instance to register.
     */
    public void registerModule(PluginModule module) {
        this.modules.add(module);
    }

    /**
     * Disables and unloads all modules gracefully.
     */
    public void unloadModules() {
        for (PluginModule module : modules) {
            try {
                module.onDisable();
                SendUtils.log("<red>Unloaded module: <yellow>" + module.getName() + "</yellow></red>");
            } catch (Exception e) {
                SendUtils.log("<dark_red>Failed to unload module: <red>" + module.getName() + "</red></dark_red>");
                plugin.getLogger().log(Level.SEVERE, "Critical error while disabling module: " + module.getName(), e);
            }
        }
        modules.clear();
    }

    /**
     * Reloads the modules.yml configuration file.
     */
    public void reloadConfig() {
        moduleConfig.reload();
    }

    /**
     * Gets the list of currently active modules.
     */
    public List<PluginModule> getModules() {
        return modules;
    }

    /**
     * Gets the module configuration manager.
     * * @return The {@link ModuleConfigManager} instance handling modules.yml.
     */
    public ModuleConfigManager getModuleConfig() {
        return moduleConfig;
    }
}
