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

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Represents the base structure for all subcommands in the plugin.
 * Implement this interface to create a new isolated command.
 */
public interface SubCommand {

    /**
     * Gets the trigger name of the subcommand (e.g., "hello", "reload").
     *
     * @return The name of the subcommand.
     */
    String getName();

    /**
     * Gets the permission node required to execute and view this command in the TabCompleter.
     *
     * @return The permission string.
     */
    String getPermission();

    /**
     * Contains the main logic of the subcommand.
     *
     * @param sender The entity (Player or Console) that executed the command.
     * @param args   The full array of arguments typed by the sender.
     */
    void execute(CommandSender sender, String[] args);

    /**
     * Handles dynamic TabCompleter suggestions specific to this subcommand.
     *
     * @param sender The entity typing the command.
     * @param args   The full array of arguments typed so far.
     * @return A list of autocomplete suggestions.
     */
    List<String> getSubcommandArguments(CommandSender sender, String[] args);
}
