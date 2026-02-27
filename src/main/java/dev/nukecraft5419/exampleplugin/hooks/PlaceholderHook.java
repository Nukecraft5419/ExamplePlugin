package dev.nukecraft5419.exampleplugin.hooks;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderHook extends PlaceholderExpansion {

    private final ExamplePlugin plugin;

    public PlaceholderHook(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "ep";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return ExamplePluginAPI.getAuthorPlugin();
    }


    @Override
    public @NotNull String getVersion() {
        return ExamplePluginAPI.getVersionPlugin();
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String identifier) {
        return PlaceholderManager.get(player, identifier);
    }
}
