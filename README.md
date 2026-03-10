<div align="center">

  <img src="https://raw.githubusercontent.com/Nukecraft5419/ExamplePlugin/refs/heads/main/assets/logo.png" alt="Plugin Logo" height="256" />
  
  <h1>🚀 Spigot Modern Template</h1>
  
  <em>A modern, modular, and ultra-lightweight architectural template for Spigot/Paper plugins, designed for maximum performance and maintainability.</em>
  
  <br>

  <p>
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/built-with/java_vector.svg" alt="Java" height="64" style="margin-right: 5px;" />
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/built-with/gradle_vector.svg" alt="Gradle" height="64" style="margin-right: 5px;" />
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/spigot_vector.svg" alt="Spigot" height="64" style="margin-right: 5px;" />
    <img src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/paper_vector.svg" alt="Paper" height="64" style="margin-right: 5px;" />
  </p>
  
  <p>
    <img src="https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 25" />
    <a href="https://github.com/nukecraft5419/ExamplePlugin/actions">
      <img src="https://img.shields.io/github/actions/workflow/status/nukecraft5419/ExamplePlugin/ci.yml?branch=main&style=for-the-badge&logo=github&label=Build" alt="Build Status" />
    </a>
    <img src="https://img.shields.io/badge/Dependabot-Enabled-blue?style=for-the-badge&logo=dependabot" alt="Dependabot" />
    <img src="https://img.shields.io/github/license/nukecraft5419/ExamplePlugin?style=for-the-badge&color=blue&label=License" alt="License" />
  </p>

  <p>
    <img src="https://img.shields.io/endpoint?url=https://ghloc.vercel.app/api/nukecraft5419/ExamplePlugin/badge&style=for-the-badge&label=Lines%20of%20Code" alt="Lines of Code" />
    <img src="https://img.shields.io/github/repo-size/nukecraft5419/ExamplePlugin?style=for-the-badge&label=Repo%20Size" alt="Repo Size" />
    <a href="https://www.codefactor.io/repository/github/nukecraft5419/exampleplugin">
      <img src="https://www.codefactor.io/repository/github/nukecraft5419/exampleplugin/badge?style=for-the-badge" alt="CodeFactor" />
    </a>
  </p>
</div>

---

## 🛠️ Technologies & Stack

* **[Spigot API](https://hub.spigotmc.org/javadocs/spigot/)**: The core foundation of the project targeting modern versions (1.21+), fully compatible with Paper.
* **[NukeLexicon](https://github.com/nukecraft5419/NukeLexicon)**: Centralized core utility API handling all i18n multi-language logic and messaging.
* **[Adventure & MiniMessage](https://docs.advntr.dev/)**: Advanced management of components, titles, sounds, and modern RGB/Hex formatting.
* **[PlaceholderAPI](https://wiki.placeholderapi.com/)**: Native integration for dynamic placeholders with safe fallbacks.
* **[bStats](https://bstats.org/)**: Anonymous metric tracking.

---

## ✨ Core Features

- 🌐 **Native Multi-Language (i18n)**: Powered by NukeLexicon. Automatically loads unlimited dynamic `.yml` locales and serves them based on the player's client language, with a configurable fallback.
- 🧩 **100% Modular Architecture**: Features, Commands, Listeners, and Hooks are completely decoupled. Turn them on or off dynamically via `modules.yml` without reloading the entire server.
- 🎨 **Legacy & Modern Text Bridge**: Seamlessly parses classic `&` codes, hex colors `&#FF0000`, and MiniMessage tags `<green>` simultaneously without breaking gradients.
- ⚙️ **Smart Configuration**: Custom UTF-8 YAML wrapper that auto-updates configuration files with new keys from the JAR while preserving user modifications.
- 🚦 **SubCommand Router**: Built-in dynamic command routing with intelligent TabCompleter and permission checks.
- 🤖 **Auto-Updating Dependencies**: GitHub Dependabot is pre-configured to keep Gradle, Paper, and all API versions up to date automatically.
- 🏗️ **Clean Centralized API**: Easily access configuration managers, module states, and server metadata via `ExamplePluginAPI`.

---

## 🚀 How to use this template

1. Click the **"Use this template"** button at the top of this GitHub repository to create your own project.
2. Clone your new repository to your local machine.
3. **Refactor the project details:**
   * Rename the `dev.nukecraft5419.exampleplugin` package to match your domain/plugin name.
   * Update `plugin.yml` (Name, version, main class path, authors).
   * Update `settings.gradle.kts` and `build.gradle.kts` with your new plugin name.
4. Run `./gradlew clean build` to compile your fresh, ready-to-code plugin!

---

## ✅ Project Roadmap

### 🏆 Completed
- [x] **Structural Setup**: Modular architecture based on Managers and Utils.
- [x] **PlaceholderAPI Integration**: PlaceholderManager implementation for custom variables.
- [x] **Core System**: Basic plugin configuration and Lifecycle management.
- [x] **Optimization**: Lean and minimal code.
- [x] **bStats**: Integration for monitoring anonymous statistics.
- [x] **ShadowJar Build Centralization**: Centralize the build process using ShadowJar.
- [x] **MiniMessage Global System**: Standardization of the color system throughout the plugin.
- [x] **Dynamic Configurations**: Native MiniMessage support for messages loaded from files.
- [x] **Legacy & PAPI Bridge**: Seamless integration of classic '&' color codes, Hex/RGB, and PlaceholderAPI directly into the MiniMessage parser.
- [x] **Modular Architecture (Core)**: Implementation of the `PluginModule` interface and a central module management system.
- [x] **Command & Listener Decoupling**: Migration of command registration and event listeners into independent modules.
- [x] **Modern CustomConfig**: Scalable wrapper with UTF-8 support and sub-folder generation.
- [x] **Advanced Module Lifecycle**: Implementation of safe `onEnable` and `onDisable` logic to prevent memory leaks.
- [x] **SubCommand Router**: Implementation of a dynamic command router for a better UX.
- [x] **Dynamic Module Toggling**: Control system via `modules.yml` to enable/disable plugin features.
- [x] **Advanced Library Loader**: Runtime dependency downloader to ensure version compatibility and minimal JAR size.
- [x] **i18n (Internationalization) Support**: Multi-language system with locales folder.

### 🚧 In Progress / To Do
- [ ] **Standardized Data Persistence**: Lightweight SQL module (SQLite/MySQL) using a clean abstraction layer.

---

## 💡 Technical Notes

The project adopts a strict modular architecture. Each component is isolated, making the template highly scalable: you can add new features without weighing down the main logic or risking conflicts. 

The entire system is designed to be compiled into an extremely lightweight JAR, relying on modern native API loaders and the centralized NukeLexicon engine to keep the server snappy. Created for clean, efficient, and professional development.

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](https://github.com/nukecraft5419/ExamplePlugin/issues). If you want to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

---

## 📄 License

This project is licensed under the MIT License.

---

<div align="center">
  <sub>Built with ❤️ by Nukecraft5419</sub>
</div>
