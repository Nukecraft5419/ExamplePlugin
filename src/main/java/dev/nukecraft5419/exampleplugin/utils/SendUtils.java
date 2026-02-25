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

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Utility class for delivering formatted messages to players and console.
 */
public class SendUtils {

    /**
     * Sends a single formatted message to a CommandSender.
     *
     * @param sender  The recipient (Player or Console).
     * @param message The raw string from config.
     */
    public static void sendMessage(CommandSender sender, String message) {
        if (sender == null || message == null || message.isEmpty()) return;

        // Extract player context if available for placeholder resolution
        Player player = (sender instanceof Player p) ? p : null;

        // Format using the existing internal engine and deliver
        sender.sendMessage(MessagesUtils.getColorMessage(player, message));
    }

    /**
     * Sends a list of formatted messages to a CommandSender.
     *
     * @param sender   The recipient (Player or Console).
     * @param messages The list of strings from config.
     */
    public static void sendMessages(CommandSender sender, List<String> messages) {
        if (sender == null || messages == null || messages.isEmpty()) return;

        for (String msg : messages) {
            sendMessage(sender, msg);
        }
    }

    /**
     * Shorthand to send a message to the console.
     *
     * @param message The message to log.
     */
    public static void log(String message) {
        sendMessage(Bukkit.getConsoleSender(), message);
    }
}
