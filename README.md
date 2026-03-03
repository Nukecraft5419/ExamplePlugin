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

### In Progress / To Do
- [ ] **TabCompleter**: Implementation for a better UX in commands.
- [ ] **Guard Clauses**: Code refactoring to eliminate excessive indentation.

## 💡 Technical Notes

The project adopts a modular architecture. Each component is isolated, making the template highly scalable: you can add new features without weighing down the main logic or risking conflicts. The entire system is designed to be compiled into an extremely lightweight JAR, keeping the server snappy.

Created for clean, efficient, and professional development.
