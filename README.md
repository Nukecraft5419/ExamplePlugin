# 🚀 Spigot Modern Template

A modern and ultra-lightweight architectural template for Spigot plugins, designed for maximum performance and maintainability. It leverages the Adventure ecosystem to deliver a cutting-edge user experience with minimal impact on server resources.

## 🛠️ Technologies Used

*   **Spigot API**: Foundation of the project.
*   **Adventure API**: Advanced management of components, titles, and sounds.
*   **MiniMessage**: Modern and powerful color formatting system.
*   **PlaceholderAPI**: Native integration for dynamic placeholders.

## ✅ Project Checklist

### Completed
- [x] **Structural Setup**: Modular architecture based on Managers and Utils.
- [x] **PlaceholderAPI Integration**: PlaceholderManager implementation for custom variables.
- [x] **Core System**: Basic plugin configuration and Lifecycle management.
- [x] **Optimization**: Lean and minimal code (~17 KB).
- [x] **bStats**: Integration for monitoring anonymous statistics.
- [x] **ShadowJar Build Centralization**: Centralize the build process using ShadowJar.
- [x] **MiniMessage Global System**: Standardization of the color system throughout the plugin.
- [x] **Dynamic Configurations**: Native MiniMessage support for messages loaded from files.
- [x] **Legacy & PAPI Bridge**: Seamless integration of classic '&' color codes, Hex/RGB, and PlaceholderAPI directly into the MiniMessage parser without breaking gradients.
- [x] **Modular Architecture (Core)**: Implementation of the `PluginModule` interface and a central module management system to decouple logic.
- [x] **Command & Listener Decoupling**: Migration of command registration and event listeners into independent, self-contained modules.
- [x] **PlaceholderAPI Soft-Dependency**: Refactoring the PAPI bridge to be a dynamic module, allowing the plugin to run without PAPI while providing internal fallbacks.
- [x] **Advanced Module Lifecycle**: Implementation of `onEnable` and `onDisable` logic for each module to prevent memory leaks and ensure clean reloads.
- [x] **SubCommand Router & TabCompleter**: Implementation of a dynamic command router for a better UX and scalable command structure.
- [x] **Guard Clauses**: Code refactoring to eliminate excessive indentation.

### In Progress / To Do
- [ ] **Modern CustomConfig**: Scalable wrapper with UTF-8 support and sub-folder generation.
- [ ] **i18n (Internationalization) Support**: Multi-language system with locales folder.
- [ ] **Dynamic Module Toggling**: Control system via `modules.yml` to enable/disable plugin features.
- [ ] **Standardized Data Persistence**: Lightweight SQL module (SQLite/MySQL) using a clean abstraction layer.
- [ ] **Advanced Library Loader**: Runtime dependency downloader (LuckPerms style) to ensure version compatibility and minimal JAR size.

## 💡 Technical Notes

The project adopts a modular architecture. Each component is isolated, making the template highly scalable: you can add new features without weighing down the main logic or risking conflicts. The entire system is designed to be compiled into an extremely lightweight JAR, keeping the server snappy.

Created for clean, efficient, and professional development.
