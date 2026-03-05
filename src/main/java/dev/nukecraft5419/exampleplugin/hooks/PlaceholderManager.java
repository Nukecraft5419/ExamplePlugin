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
