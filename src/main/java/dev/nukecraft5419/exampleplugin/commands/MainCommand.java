package dev.nukecraft5419.exampleplugin.commands;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.utils.MessagesUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
    private ExamplePlugin plugin;
    private String versionPlugin = ExamplePluginAPI.getVersionPlugin();
    private String authorPlugin = ExamplePluginAPI.getName();

    public MainCommand(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        // Console
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getErrorsConsole().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
            return true;
        }

        // Player
        Player player = (Player) sender;

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
        if (!sender.hasPermission("exampleplugin.commands.hello")) {
            sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getErrorsNoPermission().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
            return;
        }
        sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getPluginHello().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
    }

    public void subcommandHelp(CommandSender sender) {
        if (!sender.hasPermission("exampleplugin.commands.help")) {
            sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getErrorsNoPermission().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
            return;
        }
        //sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getPluginHelp().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
    }

    public void subcommandGet(CommandSender sender, String[] args) {
        // ExamplePlugin get permission
        if (!sender.hasPermission("exampleplugin.commands.get")) {
            sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getErrorsNoPermission().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
            return;
        }
        if (args.length == 1) {
            // ExamplePlugin get
            sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getErrorsNoArgsGet().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
            return;
        }
        if (args[1].equalsIgnoreCase("author")) {
            // ExamplePlugin get author
            sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getPluginAuthor().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix()).replace("%author_plugin%", authorPlugin)));
        } else if (args[1].equalsIgnoreCase("version")) {
            // ExamplePlugin get version
            sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getPluginVersion().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix()).replace("%version_plugin%", versionPlugin)));
        } else {
            // ExamplePlugin get
            sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getErrorsNoArgsGet().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
        }
    }

    // ExamplePlugin reload
    public void subcommandReload(CommandSender sender) {
        if (!sender.hasPermission("exampleplugin.commands.reload")) {
            sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getErrorsNoPermission().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
            return;
        }
        plugin.getMainConfigManager().reloadConfig();
        sender.sendMessage(MessagesUtils.getColorMessage(plugin.getMainConfigManager().getPluginReload().replace("%prefix%", plugin.getMainConfigManager().getPluginPrefix())));
    }
}
