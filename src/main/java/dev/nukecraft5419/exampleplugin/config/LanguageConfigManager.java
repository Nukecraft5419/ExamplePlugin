package dev.nukecraft5419.exampleplugin.config;

import dev.nukecraft5419.exampleplugin.ExamplePlugin;
import dev.nukecraft5419.exampleplugin.api.ExamplePluginAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the Per-Player Multi-Language (i18n) system.
 * Loads YAML files from the locales/ folder and provides messages based on the client's language.
 */
public class LanguageConfigManager {

    private final ExamplePlugin plugin;
    private final Map<String, CustomConfig> locales = new HashMap<>();

    /**
     * Initializes the LanguageConfigManager and loads all available locales.
     *
     * @param plugin The main plugin instance.
     */
    public LanguageConfigManager(ExamplePlugin plugin) {
        this.plugin = plugin;
        loadLocales();
    }

    /**
     * Loads and updates all language files from the 'locales' folder.
     * Automatically extracts default languages from the JAR if they do not exist on disk.
     */
    public void loadLocales() {
        locales.clear();

        // 1. Load default languages natively supported by the plugin
        locales.put("en_US", new CustomConfig(plugin, "en_US.yml", "locales"));
        locales.put("it_IT", new CustomConfig(plugin, "it_IT.yml", "locales"));

        // 2. Dynamically load any other custom language files added by the server admin
        File localesFolder = new File(plugin.getDataFolder(), "locales");
        if (localesFolder.exists() && localesFolder.isDirectory()) {
            File[] files = localesFolder.listFiles((dir, name) -> name.endsWith(".yml"));
            if (files != null) {
                for (File file : files) {
                    String langCode = file.getName().replace(".yml", "").toLowerCase();
                    if (!locales.containsKey(langCode)) {
                        locales.put(langCode, new CustomConfig(plugin, file.getName(), "locales"));
                    }
                }
            }
        }
    }

    /**
     * Retrieves the raw string from the correct YAML file, based on the player's client language.
     *
     * @param sender The recipient of the message (Player or Console).
     * @param path   The path to the message inside the YAML file (e.g., "plugin.hello").
     * @return The raw, unformatted string from the language file.
     */
    public String getRawMessage(CommandSender sender, String path) {
        // Get the fallback language defined in config.yml (e.g., "en_us")
        String fallback = ExamplePluginAPI.getMainConfigManager().getFallbackLanguage().toLowerCase();
        String locale = fallback;

        // If the sender is a player, read their client language directly from Minecraft
        if (sender instanceof Player player) {
            // getLocale() returns standard format strings like "it_it", "en_us", "es_es"
            locale = player.getLocale().toLowerCase();
        }

        // Find the correct language file. If the player's language doesn't exist, use the fallback.
        CustomConfig config = locales.getOrDefault(locale, locales.get(fallback));

        if (config == null) {
            return "<red>Missing locale file for: " + locale + "</red>";
        }

        // Retrieve the string from the file
        return config.getConfig().getString(path, "<red>Missing translation key: " + path + "</red>");
    }

    /**
     * Retrieves a list of raw strings from the correct YAML file, based on the player's client language.
     *
     * @param sender The recipient of the message (Player or Console).
     * @param path   The path to the string list inside the YAML file (e.g., "plugin.help").
     * @return The list of raw, unformatted strings from the language file.
     */
    public List<String> getRawMessageList(CommandSender sender, String path) {
        String fallback = ExamplePluginAPI.getMainConfigManager().getFallbackLanguage().toLowerCase();
        String locale = fallback;

        if (sender instanceof Player player) {
            locale = player.getLocale().toLowerCase();
        }

        CustomConfig config = locales.getOrDefault(locale, locales.get(fallback));

        if (config == null) {
            return java.util.List.of("<red>Missing locale file for: " + locale + "</red>");
        }

        List<String> list = config.getConfig().getStringList(path);
        return list.isEmpty() ? java.util.List.of("<red>Missing translation key (list): " + path + "</red>") : list;
    }
}
