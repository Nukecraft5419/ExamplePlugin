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
package dev.nukecraft5419.exampleplugin.api;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.config.LanguageConfigManager;
import dev.nukecraft5419.exampleplugin.config.MainConfigManager;
import dev.nukecraft5419.exampleplugin.modules.ModuleManager;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Global API entry point for ExamplePlugin.
 * <p>
 * Provides access to configuration managers, server utilities, and plugin metadata.
 * Ensure the API is registered via {@link #register(ExamplePlugin)} before use.
 */
public class ExamplePluginAPI {

    private final ExamplePlugin plugin;
    private static ExamplePluginAPI instance;
    private final MainConfigManager mainConfigManager;
    private final LanguageConfigManager languageManager;
    private BukkitAudiences adventure;
    private final ModuleManager moduleManager;

    /**
     * Internal constructor to initialize the API instance.
     *
     * @param plugin The parent {@link ExamplePlugin} instance.
     */
    @ApiStatus.Internal
    protected ExamplePluginAPI(@NotNull ExamplePlugin plugin) {
        this.plugin = plugin;
        this.mainConfigManager = new MainConfigManager(plugin);
        this.languageManager = new LanguageConfigManager(plugin);
        this.adventure = BukkitAudiences.create(plugin);
        this.moduleManager = new ModuleManager(plugin);
    }

    /**
     * Retrieves the current active instance of the API.
     *
     * @return The {@link ExamplePluginAPI} instance.
     * @throws NotRegisteredException If the API is accessed before registration.
     */
    @NotNull
    public static ExamplePluginAPI getInstance() {
        if (instance == null) {
            throw new NotRegisteredException();
        }

        return instance;
    }

    /**
     * Registers the API instance for global use.
     * Called during the plugin's enable phase.
     *
     * @param plugin The {@link ExamplePlugin} to register.
     */
    @ApiStatus.Internal
    public static void register(@NotNull ExamplePlugin plugin) {
        instance = new ExamplePluginAPI(plugin);
    }

    /**
     * Unregisters the API instance and cleans up references.
     * Called during the plugin's disable phase to prevent memory leaks.
     */
    @ApiStatus.Internal
    public static void unregister() {
        if (instance != null) {
            if (instance.adventure != null) {
                instance.adventure.close();
                instance.adventure = null;
            }
        }
        instance = null;
    }

    /**
     * Gets the current version of the plugin as defined in plugin.yml.
     *
     * @return The plugin version string.
     */
    @NotNull
    public static String getVersionPlugin() {
        return getInstance().plugin.getDescription().getVersion();
    }

    /**
     * Gets the first author listed in the plugin description.
     *
     * @return The name of the primary author.
     */
    public static String getAuthorPlugin() {
        return getInstance().plugin.getDescription().getAuthors().getFirst();
    }

    /**
     * Provides access to the main configuration manager.
     *
     * @return The {@link MainConfigManager} instance.
     */
    public static MainConfigManager getMainConfigManager() {
        return getInstance().mainConfigManager;
    }

    /**
     * Gets the Minecraft API version the plugin is targeting.
     *
     * @return The API version (e.g., "1.21").
     */
    public static String getServerApiVersion(){
        return getInstance().plugin.getDescription().getAPIVersion();
    }

    /**
     * Gets the full Bukkit version of the server.
     *
     * @return The server version string.
     */
    @NotNull
    public static String getServerVersion() {
        return Bukkit.getBukkitVersion();
    }

    /**
     * Provides access to the Adventure BukkitAudiences instance.
     *
     * @return The {@link BukkitAudiences} instance.
     */
    @NotNull
    public static BukkitAudiences getAdventure() {
        if (getInstance().adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return getInstance().adventure;
    }

    /**
     * Retrieves the central ModuleManager instance.
     * Use this method to interact with the plugin's module lifecycle
     * (e.g., getting a list of active modules or reloading their configs).
     *
     * @return The {@link ModuleManager} instance.
     */
    public static ModuleManager getModuleManager() {
        return getInstance().moduleManager;
    }

    public static LanguageConfigManager getLanguageManager() {
        return getInstance().languageManager;
    }

    /**
     * Exception thrown when the API is accessed without being properly registered.
     */
    static final class NotRegisteredException extends IllegalStateException {

        private static final String MESSAGE = """

            [ExamplePlugin] API Access Error:
            The API has not been registered yet. This usually happens because:

            1. The plugin failed to load or is disabled.
            2. You are accessing the API too early (e.g., in the constructor or onLoad).
               -> Solution: Access the API only after or during the onEnable() phase.
            3. The API was incorrectly shaded into your JAR.

            Current Status: Not Registered / Instance is null.
            """;

        NotRegisteredException() {
            super(MESSAGE);
        }
    }
}
