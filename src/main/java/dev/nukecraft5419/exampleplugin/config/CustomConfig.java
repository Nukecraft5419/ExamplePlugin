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
package dev.nukecraft5419.exampleplugin.config;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.utils.ConfigUpdaterUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

/**
 * Modern wrapper for Bukkit's YamlConfiguration.
 * Fully supports UTF-8, sub-folders, and automatic default loading from the JAR.
 */
public class CustomConfig {

    private final ExamplePlugin plugin;
    private final String resourcePath;
    private final File file;
    private FileConfiguration config;

    /**
     * Creates or loads a custom YAML configuration file in the main plugin folder.
     *
     * @param plugin   The main plugin instance.
     * @param fileName The name of the file (e.g., "config.yml").
     */
    public CustomConfig(@NotNull ExamplePlugin plugin, @NotNull String fileName) {
        this(plugin, fileName, null);
    }

    /**
     * Creates or loads a custom YAML configuration file in a specific subfolder.
     *
     * @param plugin   The main plugin instance.
     * @param fileName The name of the file (e.g., "spawn.yml" or just "spawn").
     * @param folder   The subfolder (e.g., "locales"). Use null for the main folder.
     */
    public CustomConfig(@NotNull ExamplePlugin plugin, @NotNull String fileName, @Nullable String folder) {
        this.plugin = plugin;
        String actualFileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";

        if (folder != null && !folder.isEmpty()) {
            this.file = new File(plugin.getDataFolder() + File.separator + folder, actualFileName);
            this.resourcePath = folder + "/" + actualFileName;
        } else {
            this.file = new File(plugin.getDataFolder(), actualFileName);
            this.resourcePath = actualFileName;
        }

        saveDefaultConfig();
        reloadConfig();
    }

    /**
     * Reloads the configuration from the disk.
     * If a default file exists inside the JAR, it will act as a fallback for missing keys.
     * It also automatically updates the file on disk without losing comments.
     */
    public void reloadConfig() {
        // Automatically update the config with new keys before loading it into memory
        ConfigUpdaterUtils.update(plugin, this.resourcePath, this.file);

        this.config = YamlConfiguration.loadConfiguration(this.file);

        // Look for default configs inside the JAR and load them using UTF-8
        InputStream defaultStream = plugin.getResource(this.resourcePath);
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream, StandardCharsets.UTF_8));
            this.config.setDefaults(defaultConfig);
        }
    }

    /**
     * Gets the loaded configuration instance.
     *
     * @return The FileConfiguration to read/write data.
     */
    @NotNull
    public FileConfiguration getConfig() {
        if (this.config == null) reloadConfig();
        return this.config;
    }

    /**
     * Saves the current memory configuration to the disk.
     */
    @SuppressWarnings("unused")
    public void saveConfig() {
        try {
            getConfig().save(this.file);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.file.getName(), e);
        }
    }

    /**
     * Creates the file and directories if they don't exist.
     * It will copy the default file from the JAR if available.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void saveDefaultConfig() {
        if (!this.file.exists()) {
            File parent = this.file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs(); // Intentionally ignoring the boolean result
            }

            if (plugin.getResource(this.resourcePath) != null) {
                plugin.saveResource(this.resourcePath, false);
            } else {
                // Otherwise, create a completely empty file
                try {
                    this.file.createNewFile(); // Intentionally ignoring the boolean result
                } catch (IOException e) {
                    plugin.getLogger().log(Level.SEVERE, "Could not create empty file " + this.file.getName(), e);
                }
            }
        }
    }
}
