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
import dev.nukecraft5419.exampleplugin.utils.SendUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ModuleManager {

    private final ExamplePlugin plugin;
    private final List<PluginModule> modules = new ArrayList<>();

    public ModuleManager(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers and enables all active modules.
     */
    public void loadModules() {
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
}
