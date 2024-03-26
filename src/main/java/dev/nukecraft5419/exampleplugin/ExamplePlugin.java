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
package dev.nukecraft5419.exampleplugin;

import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.commands.MainCommand;
import dev.nukecraft5419.exampleplugin.config.MainConfigManager;
import dev.nukecraft5419.exampleplugin.listeners.PlayerJoinListener;
import dev.nukecraft5419.exampleplugin.utils.MessagesUtils;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {
    private MainConfigManager mainConfigManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        new ExamplePluginAPI(this);
        // Register commands
        registerCommands();
        // Register events
        registerEvents();
        mainConfigManager = new MainConfigManager(this);
        ExamplePluginAPI.getServer().sendMessage(MessagesUtils.getColorMessage("%prefix% &asuccessfully enabled!".replace("%prefix%", this.getMainConfigManager().getPluginPrefix())));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ExamplePluginAPI.getServer().sendMessage(MessagesUtils.getColorMessage("%prefix% &cwas successfully disabled!".replace("%prefix%", this.getMainConfigManager().getPluginPrefix())));
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }

    public void registerCommands() {
        this.getCommand("exampleplugin").setExecutor(new MainCommand(this));
    }

    private void addListener(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    public void registerEvents() {
        this.addListener(new PlayerJoinListener(this));
    }
}
