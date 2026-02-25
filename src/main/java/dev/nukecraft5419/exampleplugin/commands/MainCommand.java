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
import dev.nukecraft5419.exampleplugin.utils.PermissionsUtils;
import dev.nukecraft5419.exampleplugin.utils.SendUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    private final ExamplePlugin plugin;
    MainConfigManager config = ExamplePluginAPI.getMainConfigManager();

    public MainCommand(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            // Console
            SendUtils.sendMessage(sender, config.getErrorsConsole());
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
        if (!sender.hasPermission(PermissionsUtils.COMMAND_HELLO)) {
            SendUtils.sendMessage(sender, config.getErrorsNoPermission());
            return;
        }
        SendUtils.sendMessage(sender, config.getPluginHello());
    }

    public void subcommandHelp(CommandSender sender) {
        if (!sender.hasPermission(PermissionsUtils.COMMAND_HELP)) {
            SendUtils.sendMessage(sender, config.getErrorsNoPermission());
            return;
        }
        SendUtils.sendMessages(sender, config.getPluginHelp());
    }

    public void subcommandGet(CommandSender sender, String[] args) {
        // ExamplePlugin get permission
        if (!sender.hasPermission(PermissionsUtils.COMMAND_GET)) {
            SendUtils.sendMessage(sender, config.getErrorsNoPermission());
            return;
        }

        if (args.length == 1) {
            // ExamplePlugin get
            SendUtils.sendMessage(sender, config.getErrorsNoArgsGet());
            return;
        }

        if (args[1].equalsIgnoreCase("author")) {
            // ExamplePlugin get author
            SendUtils.sendMessage(sender, config.getPluginAuthor());
        } else if (args[1].equalsIgnoreCase("version")) {
            // ExamplePlugin get version
            SendUtils.sendMessage(sender, config.getPluginVersion());
        } else {
            // ExamplePlugin get
            SendUtils.sendMessage(sender, config.getErrorsNoArgsGet());
        }
    }

    // ExamplePlugin reload
    public void subcommandReload(CommandSender sender) {
        if (!sender.hasPermission(PermissionsUtils.COMMAND_RELOAD)) {
            SendUtils.sendMessage(sender, config.getErrorsNoPermission());
            return;
        }
        config.reloadConfig();
        SendUtils.sendMessage(sender, config.getPluginReload());
    }
}
