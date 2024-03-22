package dev.nukecraft5419.exampleplugin.utils;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.commands.MainCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class RegisterCommandsUtils {
    private ExamplePlugin plugin;

    public RegisterCommandsUtils(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommands() {
        plugin.getCommand("exampleplugin").setExecutor(new MainCommand(plugin));
    }
}
