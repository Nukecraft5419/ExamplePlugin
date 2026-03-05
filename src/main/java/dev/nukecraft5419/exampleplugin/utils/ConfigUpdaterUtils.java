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

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;

/**
 * A robust YAML configuration updater.
 * It intelligently merges new keys from the JAR defaults into the existing file on disk,
 * preserving all user values, nested keys, and comments.
 */
public class ConfigUpdaterUtils {

    /**
     * Updates a YAML configuration file by merging it with the default file in the JAR.
     *
     * @param plugin   The plugin instance.
     * @param fileName The name of the file (e.g., "config.yml").
     * @param file     The actual file on the disk to update.
     */
    public static void update(Plugin plugin, String fileName, File file) {
        if (!file.exists()) return;

        InputStream defaultStream = plugin.getResource(fileName);
        if (defaultStream == null) return;

        try {
            FileConfiguration currentConfig = YamlConfiguration.loadConfiguration(file);
            FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream, StandardCharsets.UTF_8));

            // If the current config has all the keys of the default one, no update is needed
            if (currentConfig.getKeys(true).containsAll(defaultConfig.getKeys(true))) {
                return;
            }

            List<String> currentLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            List<String> defaultLines = readLinesFromStream(plugin.getResource(fileName));
            List<String> newLines = new ArrayList<>();

            Map<String, String> currentKeysAndValues = parseYamlLines(currentLines);

            boolean isUpdated = false;
            String currentPath = "";

            for (String line : defaultLines) {
                if (line.trim().startsWith("#") || line.trim().isEmpty() || line.trim().startsWith("-")) {
                    newLines.add(line);
                    continue;
                }

                String key = getKeyFromLine(line);
                int indentation = getIndentation(line);
                currentPath = updatePath(currentPath, key, indentation);

                if (currentConfig.contains(currentPath)) {
                    // User has this key, keep their value
                    String userValue = currentKeysAndValues.get(currentPath);
                    if (userValue != null) {
                        newLines.add(getIndentationString(indentation) + key + ":" + userValue);
                    } else {
                        newLines.add(line);
                    }
                } else {
                    // New key from the update! Add it.
                    newLines.add(line);
                    isUpdated = true;
                }
            }

            if (isUpdated) {
                Files.write(file.toPath(), newLines, StandardCharsets.UTF_8);
                plugin.getLogger().info("Successfully updated " + fileName + " with new configuration keys!");
            }

        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to update configuration file: " + fileName, e);
        }
    }

    private static List<String> readLinesFromStream(InputStream stream) throws IOException {
        List<String> lines = new ArrayList<>();
        if (stream == null) return lines;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static Map<String, String> parseYamlLines(List<String> lines) {
        Map<String, String> map = new LinkedHashMap<>();
        String currentPath = "";
        for (String line : lines) {
            if (line.trim().startsWith("#") || line.trim().isEmpty() || line.trim().startsWith("-")) continue;

            String key = getKeyFromLine(line);
            if (key == null) continue;

            int indentation = getIndentation(line);
            currentPath = updatePath(currentPath, key, indentation);

            String value = getValueFromLine(line);
            // Removed redundant null check
            if (!value.isEmpty()) {
                map.put(currentPath, value);
            }
        }
        return map;
    }

    private static String updatePath(String currentPath, String key, int indentation) {
        if (indentation == 0) return key;
        String[] parts = currentPath.split("\\.");
        int level = indentation / 2;

        StringBuilder newPath = new StringBuilder();
        for (int i = 0; i < Math.min(level, parts.length); i++) {
            newPath.append(parts[i]).append(".");
        }
        // Fixed unnecessary toString() call before appending
        return newPath.append(key).toString();
    }

    private static String getKeyFromLine(String line) {
        int colonIndex = line.indexOf(':');
        if (colonIndex == -1) return null;
        return line.substring(0, colonIndex).trim();
    }

    private static String getValueFromLine(String line) {
        int colonIndex = line.indexOf(':');
        if (colonIndex == -1 || colonIndex == line.length() - 1) return "";
        return line.substring(colonIndex + 1);
    }

    private static int getIndentation(String line) {
        int spaces = 0;
        for (char c : line.toCharArray()) {
            if (c == ' ') spaces++;
            else break;
        }
        return spaces;
    }

    private static String getIndentationString(int spaces) {
        // Replaced loop with modern String.repeat()
        return " ".repeat(spaces);
    }
}
