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
import dev.nukecraft5419.exampleplugin.commands.subcommands.GetCommand;
import dev.nukecraft5419.exampleplugin.commands.subcommands.HelloCommand;
import dev.nukecraft5419.exampleplugin.commands.subcommands.HelpCommand;
import dev.nukecraft5419.exampleplugin.commands.subcommands.ReloadCommand;
import dev.nukecraft5419.exampleplugin.config.MainConfigManager;
import dev.nukecraft5419.exampleplugin.config.ModuleConfigManager;
import dev.nukecraft5419.exampleplugin.utils.SendUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Initializes the main command router.
 * Registers core subcommands and dynamically loads optional ones based on modules.yml.
 *
 * @param plugin The main plugin instance.
 */
public class MainCommand implements CommandExecutor, TabCompleter {

    private final ExamplePlugin plugin;
    private final MainConfigManager config = ExamplePluginAPI.getMainConfigManager();
    private final List<SubCommand> subCommands = new ArrayList<>();

    public MainCommand(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;

        ModuleConfigManager moduleConfig = ExamplePluginAPI.getModuleManager().getModuleConfig();

        subCommands.add(new HelpCommand());
        subCommands.add(new GetCommand());
        subCommands.add(new ReloadCommand());

        if (moduleConfig.isCommandEnabled("hello")) {
            subCommands.add(new HelloCommand());
            SendUtils.log("<green>Loaded command:</green> <yellow>hello</yellow>");
        }
    }

    /**
     * Executes the main command logic.
     * Acts as a router: finds the matching SubCommand and delegates execution.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            SendUtils.sendMessage(sender, config.getErrorsConsole());
            return true;
        }

        if (args.length == 0) {
            executeSubCommand(sender, "help", args);
            return true;
        }

        executeSubCommand(sender, args[0], args);
        return true;
    }

    /**
     * Helper method to find and execute a specific subcommand.
     * Also handles permission checks before execution.
     *
     * @param sender      The command sender.
     * @param commandName The name of the subcommand to search for.
     * @param args        The full arguments array.
     */
    private void executeSubCommand(CommandSender sender, String commandName, String[] args){
        for (SubCommand subCmd : subCommands) {
            if (subCmd.getName().equalsIgnoreCase(commandName)) {
                if (sender.hasPermission(subCmd.getPermission())) {
                    subCmd.execute(sender, args);
                } else {
                    SendUtils.sendMessage(sender, config.getErrorsNoPermission());
                }
                return;
            }
        }
        executeSubCommand(sender, "help", args);
    }

    /**
     * Provides dynamic tab completion.
     * Automatically queries the registered SubCommands for relevant suggestions.
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            for (SubCommand subCmd : subCommands) {
                if (sender.hasPermission(subCmd.getPermission())) {
                    suggestions.add(subCmd.getName());
                }
            }
        } else if (args.length >= 2) {
            for (SubCommand subCmd : subCommands) {
                if (args[0].equalsIgnoreCase(subCmd.getName()) && sender.hasPermission(subCmd.getPermission())) {
                    suggestions = subCmd.getSubcommandArguments(sender, args);
                    break;
                }
            }
        }
        return suggestions.stream()
            .filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
            .collect(Collectors.toList());
    }
}
