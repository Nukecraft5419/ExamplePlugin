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
package dev.nukecraft5419.exampleplugin.commands;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.config.MainConfigManager;
import dev.nukecraft5419.exampleplugin.utils.MessagesUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainCommand implements CommandExecutor {

    private final ExamplePlugin plugin;
    private final String versionPlugin = ExamplePluginAPI.getVersionPlugin();
    private final String authorPlugin = ExamplePluginAPI.getName();
    MainConfigManager mainConfigManager = ExamplePluginAPI.getMainConfigManager();

    public MainCommand(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            // Console
            sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getErrorsConsole().replace("%prefix%", mainConfigManager.getPluginPrefix())));
            return true;
        }

        // ExamplePlugin args[0] args[1] args[2]
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("hello")) {
                // ExamplePlugin hello
                subcommandHello(sender);
            } else if (args[0].equalsIgnoreCase("get")) {
                // ExamplePlugin <author/version>
                subcommandGet(sender, args);
            } else if (args[0].equalsIgnoreCase("reload")) {
                // ExamplePlugin reload
                subcommandReload(sender);
            } else {
                // ExamplePlugin help
                subcommandHelp(sender);
            }
        } else {
            // ExamplePlugin help
            subcommandHelp(sender);
        }

        return true;
    }

    // ExamplePlugin reload
    public void subcommandHello(CommandSender sender) {
        Player player = (Player) sender;
        if (!sender.hasPermission("exampleplugin.commands.hello")) {
            sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getErrorsNoPermission().replace("%prefix%", mainConfigManager.getPluginPrefix())));
            return;
        }
        sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getPluginHello().replace("%prefix%", mainConfigManager.getPluginPrefix()).replace("%display_name%", player.getDisplayName())));
    }

    public void subcommandHelp(CommandSender sender) {
        if (!sender.hasPermission("exampleplugin.commands.help")) {
            sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getErrorsNoPermission().replace("%prefix%", mainConfigManager.getPluginPrefix())));
            return;
        }

        List<String> messages = mainConfigManager.getPluginHelp();
        for (String m : messages) {
            sender.sendMessage(MessagesUtils.getColorMessage(m.replace("%prefix%", mainConfigManager.getPluginPrefix())));
        }
    }

    public void subcommandGet(CommandSender sender, String[] args) {
        // ExamplePlugin get permission
        if (!sender.hasPermission("exampleplugin.commands.get")) {
            sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getErrorsNoPermission().replace("%prefix%", mainConfigManager.getPluginPrefix())));
            return;
        }

        if (args.length == 1) {
            // ExamplePlugin get
            sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getErrorsNoArgsGet().replace("%prefix%", mainConfigManager.getPluginPrefix())));
            return;
        }

        if (args[1].equalsIgnoreCase("author")) {
            // ExamplePlugin get author
            sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getPluginAuthor().replace("%prefix%", mainConfigManager.getPluginPrefix()).replace("%author%", authorPlugin)));
        } else if (args[1].equalsIgnoreCase("version")) {
            // ExamplePlugin get version
            sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getPluginVersion().replace("%prefix%", mainConfigManager.getPluginPrefix()).replace("%version%", versionPlugin)));
        } else {
            // ExamplePlugin get
            sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getErrorsNoArgsGet().replace("%prefix%", mainConfigManager.getPluginPrefix())));
        }
    }

    // ExamplePlugin reload
    public void subcommandReload(CommandSender sender) {
        if (!sender.hasPermission("exampleplugin.commands.reload")) {
            sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getErrorsNoPermission().replace("%prefix%", mainConfigManager.getPluginPrefix())));
            return;
        }
        mainConfigManager.reloadConfig();
        sender.sendMessage(MessagesUtils.getColorMessage(mainConfigManager.getPluginReload().replace("%prefix%", mainConfigManager.getPluginPrefix())));
    }
}
