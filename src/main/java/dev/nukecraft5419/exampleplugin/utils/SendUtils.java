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
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Utility class for delivering formatted messages to players and console.
 * Utilizes Adventure's BukkitAudiences to safely send modern Components on Spigot servers.
 */
public class SendUtils {

    /**
     * Formats and sends a single MiniMessage string to a CommandSender.
     *
     * @param sender  The recipient (Player or Console).
     * @param message The raw string containing MiniMessage tags (and optionally PAPI placeholders).
     */
    public static void sendMessage(CommandSender sender, String message) {
        if (sender == null || message == null || message.isEmpty()) return;

        // 1. Convert the raw string into an Adventure Component using our format engine
        Component component = MessagesUtils.format(sender, message);

        // 2. Deliver the Component using the Adventure Platform bridge.
        // This is necessary because native Spigot CommandSenders do not support Component objects directly.
        ExamplePluginAPI.getAdventure().sender(sender).sendMessage(component);
    }

    /**
     * Formats and sends a list of MiniMessage strings to a CommandSender.
     *
     * @param sender   The recipient (Player or Console).
     * @param messages The list of raw strings to send.
     */
    public static void sendMessages(CommandSender sender, List<String> messages) {
        if (sender == null || messages == null || messages.isEmpty()) return;

        for (String msg : messages) {
            sendMessage(sender, msg);
        }
    }

    /**
     * Shorthand to send a formatted message directly to the server console.
     * Useful for startup, shutdown, or debugging logs.
     *
     * @param message The message to log (supports custom tags like <prefix> and colors like <green>).
     */
    public static void log(String message) {
        sendMessage(Bukkit.getConsoleSender(), message);
    }
}
