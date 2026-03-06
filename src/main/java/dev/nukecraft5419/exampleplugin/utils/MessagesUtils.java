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
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for handling chat messages, formatting, and placeholders.
 * Powered by Adventure API and MiniMessage for modern, component-based text.
 */
public class MessagesUtils {

    // Thread-safe MiniMessage instance
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    /**
     * Formats a raw string into an Adventure Component.
     * Dynamically translates classic %placeholder% syntax into MiniMessage <papi:placeholder> tags
     * to preserve RGB gradients and modern formatting while fully supporting PlaceholderAPI.
     *
     * @param sender  The CommandSender context (used for player-specific placeholders).
     * @param message The raw string containing MiniMessage tags and PAPI placeholders.
     * @return The formatted Adventure Component.
     */
    public static Component format(CommandSender sender, String message) {
        if (message == null || message.isEmpty()) return Component.empty();

        Player player = (sender instanceof Player p) ? p : null;

        message = convertLegacyToMiniMessage(message);

        // STEP 1: Auto-translate legacy PAPI syntax
        // Converts %server_tps% into <papi:server_tps> so users don't have to change their habits.
        if (player != null && isPapiEnabled() && message.contains("%")) {
            message = message.replaceAll("%([^%]+)%", "<papi:$1>");
        }

        // STEP 2: Build Tag Resolvers
        // Combine our internal custom tags (<prefix>) with the PAPI dynamic resolver.
        TagResolver internalTags = buildInternalResolvers(player);
        TagResolver papiResolver = (player != null && isPapiEnabled()) ? createSafePapiResolver(player) : TagResolver.empty();

        // STEP 3: Deserialize the message
        // MiniMessage processes standard tags, our internal tags, and dynamically fetches PAPI values
        // safely injecting them as components without breaking RGB gradients in the surrounding text.
        return MINI_MESSAGE.deserialize(message, TagResolver.resolver(internalTags, papiResolver));
    }

    /**
     * Checks if PlaceholderAPI is currently enabled on the server.
     */
    private static boolean isPapiEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    /**
     * Official PlaceholderAPI TagResolver (Credit to Adventure Wiki / mbaxter).
     * This safely converts PAPI's legacy color returns into modern Components
     * without destroying RGB gradients in the rest of the message.
     * Safely constructs the PAPI TagResolver.
     * It uses a wrapper class so the JVM doesn't crash if PAPI is missing.
     * @param player The player context for PlaceholderAPI.
     * @return A TagResolver capable of parsing <papi:...> tags.
     */
    private static @NotNull TagResolver createSafePapiResolver(final @NotNull Player player) {
        return TagResolver.resolver("papi", (argumentQueue, context) -> {
            // Get the string placeholder that they want to use (e.g., "server_tps").
            final String papiPlaceholder = argumentQueue.popOr("papi tag requires an argument").value();

            // Call PAPI by re-adding the % % symbols we removed earlier.
            String parsedPlaceholder = PapiWrapper.set(player, "%" + papiPlaceholder + "%");

            // Convert the result (which might contain legacy § codes) into a Component.
            final Component componentPlaceholder = LegacyComponentSerializer.legacySection().deserialize(parsedPlaceholder);

            // Insert the Component directly into the MiniMessage tree.
            return Tag.selfClosingInserting(componentPlaceholder);
        });
    }

    /**
     * Constructs the custom TagResolvers for the plugin's internal placeholders.
     * Uses MiniMessage syntax (e.g., {@code <prefix>}, {@code <version>} instead of %prefix%).
     * Uses safe fallbacks to prevent NullPointerExceptions if the config or API is not ready.
     *
     * @param player The player context (can be null if sender is Console).
     * @return A TagResolver containing all registered custom tags.
     */
    private static TagResolver buildInternalResolvers(Player player) {

        // Safe fallbacks: If the config is broken or missing, these prevent MiniMessage from crashing.
        String prefix = ExamplePluginAPI.getLanguageManager().getRawMessage(player, "plugin.prefix");


        if (prefix == null || prefix.contains("Missing translation") || prefix.contains("Missing locale file")) {
            prefix = "<gray>[ExamplePlugin]</gray>";
        }

        // Guaranteed to be non-null by the API annotations
        String version = ExamplePluginAPI.getVersionPlugin();
        String serverVersion = ExamplePluginAPI.getServerVersion();

        // Defensive checks for methods that might lack strict @NotNull annotations
        String author = ExamplePluginAPI.getAuthorPlugin() != null ? ExamplePluginAPI.getAuthorPlugin() : "Unknown";
        String serverApiVersion = ExamplePluginAPI.getServerApiVersion() != null ? ExamplePluginAPI.getServerApiVersion() : "Unknown";

        TagResolver.Builder builder = TagResolver.builder()
            // Placeholder.parsed(): Allows the replacement string to contain its own MiniMessage tags
            // (e.g., if prefix in config is "<gray>[<gold>Plugin</gold>]</gray>")
            .resolver(Placeholder.parsed("prefix", prefix))

            // Placeholder.unparsed(): Injects raw text securely. MiniMessage will NOT parse tags inside these strings.
            // Prevents visual exploits if a player name or version string contains malicious formatting tags.
            .resolver(Placeholder.unparsed("author", author))
            .resolver(Placeholder.unparsed("version", version))
            .resolver(Placeholder.unparsed("server_version", serverVersion))
            .resolver(Placeholder.unparsed("server_api_version", serverApiVersion));

        // Add player-specific internal tags if a player context exists
        if (player != null) {
            builder.resolver(Placeholder.unparsed("player_name", player.getName()));
            builder.resolver(Placeholder.unparsed("name", player.getDisplayName()));
        } else {
            builder.resolver(Placeholder.unparsed("name", "Console"));
            builder.resolver(Placeholder.unparsed("player_name", "Console"));
        }

        return builder.build();
    }

    /**
     * Translates legacy color codes (e.g., &6, &l, &#FF0000) into MiniMessage tags.
     * This allows users to keep using their muscle memory for older color formatting
     * while still enjoying the power of the MiniMessage parser.
     *
     * @param message The raw string with legacy codes.
     * @return The string with MiniMessage tags injected.
     */
    private static String convertLegacyToMiniMessage(String message) {
        if (!message.contains("&")) return message;

        // Convert Hex colors: &#FF0000 -> <#FF0000>
        message = message.replaceAll("&#([a-fA-F0-9]{6})", "<#$1>");

        // Convert standard colors & formatting
        return message.replace("&0", "<black>")
            .replace("&1", "<dark_blue>")
            .replace("&2", "<dark_green>")
            .replace("&3", "<dark_aqua>")
            .replace("&4", "<dark_red>")
            .replace("&5", "<dark_purple>")
            .replace("&6", "<gold>")
            .replace("&7", "<gray>")
            .replace("&8", "<dark_gray>")
            .replace("&9", "<blue>")
            .replace("&a", "<green>")
            .replace("&b", "<aqua>")
            .replace("&c", "<red>")
            .replace("&d", "<light_purple>")
            .replace("&e", "<yellow>")
            .replace("&f", "<white>")
            .replace("&l", "<bold>")
            .replace("&m", "<strikethrough>")
            .replace("&n", "<underlined>")
            .replace("&o", "<italic>")
            .replace("&k", "<obfuscated>")
            .replace("&r", "<reset>");
    }

    /**
     * Isolated Wrapper class for PlaceholderAPI.
     * <p>
     * This is a clever Java trick: the JVM will only attempt to load this class
     * (and consequently look for the PAPI jar) if the isPapiEnabled() check passes.
     * This completely prevents NoClassDefFoundError on servers without PAPI.
     * </p>
     */
    private static class PapiWrapper {

        /**
         * Parses the PAPI placeholders safely.
         *
         * @param player The player to parse placeholders for.
         * @param text   The raw string containing %placeholders%.
         * @return The string with values replaced by PlaceholderAPI.
         */
        static String set(Player player, String text) {
            return PlaceholderAPI.setPlaceholders(player, text);
        }
    }
}
