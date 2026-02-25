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
package dev.nukecraft5419.exampleplugin.utils;

import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import dev.nukecraft5419.exampleplugin.config.MainConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for handling chat messages, color formatting, and placeholders.
 */
public class MessagesUtils {

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("%[a-zA-Z0-9_]+%");

    /**
     * Translates placeholders and color codes in a message.
     *
     * @param player  The player for context-based placeholders (like %display_name%), can be null.
     * @param message The raw message string from the configuration.
     * @return The formatted string with colors and replaced placeholders.
     */
    public static String getColorMessage(Player player, String message) {
        if (message == null || message.isEmpty()) return "";

        Matcher matcher = PLACEHOLDER_PATTERN.matcher(message);
        StringBuilder builder = new StringBuilder();

        while (matcher.find()) {
            String placeholder = matcher.group();
            String replacement = getReplacement (player, placeholder);
            matcher.appendReplacement(builder, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(builder);

        return ChatColor.translateAlternateColorCodes('&', builder.toString());
    }

    /**
     * Core logic for placeholder replacement.
     * * @param player      The player context.
     * @param placeholder The placeholder found (e.g., %prefix%).
     * @return The replacement string or the placeholder itself if not found.
     */
    private static String getReplacement(Player player, String placeholder) {
        MainConfigManager config = ExamplePluginAPI.getMainConfigManager();

        return switch (placeholder) {
            case "%prefix%" -> config.getPluginPrefix();
            case "%version%" -> ExamplePluginAPI.getVersionPlugin();
            case "%author%" -> ExamplePluginAPI.getAuthorPlugin();
            case "%display_name%" -> (player != null) ? player.getDisplayName() : "Console";
            case "%server_version%" -> ExamplePluginAPI.getServerVersion();
            case "%server_api_version%" -> ExamplePluginAPI.getServerApiVersion();
            default -> placeholder;
        };
    }
}
