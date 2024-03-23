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
package dev.nukecraft5419.exampleplugin.api;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;

public class ExamplePluginAPI {
    private static ExamplePlugin plugin;
    private static final Server server = Bukkit.getServer();

    public ExamplePluginAPI(ExamplePlugin plugin) {
        ExamplePluginAPI.plugin = plugin;
    }

    public static String getVersionPlugin() {
        return  plugin.getDescription().getVersion();
    }

    public static String getName() {
        return plugin.getDescription().getAuthors().get(0);
    }

    public static String getServerApiVersion(){
        return plugin.getDescription().getAPIVersion();
    }

    public static ConsoleCommandSender getServer() {
        Server server = Bukkit.getServer();
        return server.getConsoleSender();
    }

    public static String getServerVersion() {
        String pkg = server.getClass().getPackage().getName();
        return pkg.substring(pkg.lastIndexOf('.') + 1);
    }
}
