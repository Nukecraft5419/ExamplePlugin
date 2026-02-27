package dev.nukecraft5419.exampleplugin.hooks;

import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Manages the logic for all plugin placeholders.
 * This class serves as a central registry to provide data for both
 * internal messages and external PlaceholderAPI expansions.
 */
public class PlaceholderManager {

    /**
     * Retrieves the value associated with a specific placeholder identifier.
     *
     * @param player     The player context (can be null for console or general placeholders).
     * @param identifier The placeholder name without '%' symbols (e.g., "version").
     * @return The replaced value, or null if the identifier is not recognized.
     */
    public static @Nullable String get(@Nullable Player player, @NotNull String identifier) {
        return switch (identifier.toLowerCase()) {
            // %ep_name% -> Returns the player's display name or "Console"
            case "name" -> (player != null) ? player.getDisplayName() : "Console";

            // %ep_version% -> Returns the current plugin version from the API
            case "version" -> ExamplePluginAPI.getVersionPlugin();

            // Add more cases here as your plugin grows!
            default -> null;
        };
    }
}
