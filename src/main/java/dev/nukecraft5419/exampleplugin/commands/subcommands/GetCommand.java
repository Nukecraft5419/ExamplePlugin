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
package dev.nukecraft5419.exampleplugin.commands.subcommands;

import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.commands.SubCommand;
import dev.nukecraft5419.exampleplugin.config.MainConfigManager;
import dev.nukecraft5419.exampleplugin.utils.PermissionsUtils;
import dev.nukecraft5419.exampleplugin.utils.SendUtils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the '/exampleplugin get <author|version>' command.
 * Retrieves internal plugin information.
 */
public class GetCommand implements SubCommand {

    private final MainConfigManager config = ExamplePluginAPI.getMainConfigManager();

    @Override
    public String getName() {
        return "get";
    }

    @Override
    public String getPermission() {
        return PermissionsUtils.COMMAND_GET;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length < 2) {
            SendUtils.sendMessage(sender, config.getErrorsNoArgsGet());
            return;
        }

        // Resolves the requested info argument
        switch (args[1].toLowerCase()) {
            case "author" -> SendUtils.sendMessage(sender, config.getPluginAuthor());
            case "version" -> SendUtils.sendMessage(sender, config.getPluginVersion());
            default -> SendUtils.sendMessage(sender, config.getErrorsNoArgsGet());
        }
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 2) {
            suggestions.add("author");
            suggestions.add("version");
        }

        return suggestions;
    }
}
